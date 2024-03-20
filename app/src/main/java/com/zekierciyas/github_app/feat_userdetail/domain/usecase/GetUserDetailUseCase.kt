package com.zekierciyas.github_app.feat_userdetail.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.feat_userdetail.data.local.repository.UserDetailLocalRepoImp
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userdetail.data.repository.UserDetailRemoteRepoImp
import com.zekierciyas.github_app.feat_userdetail.domain.mapper.UserDetailMapper
import com.zekierciyas.github_app.feat_userdetail.domain.model.UserDetailDomainModel
import com.zekierciyas.github_app.feat_userlist.data.local.CacheExpiryDatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val remoteRepo: UserDetailRemoteRepoImp,
    private val localRepo: UserDetailLocalRepoImp,
    private val userFavRepo: FavUserRepositoryImp,
    private val cacheExpiryDatePolicy: CacheExpiryDatePolicy,
    private val mapper: UserDetailMapper
): BaseUseCase<String, DataState<UserDetailDomainModel>> {
    override suspend fun execute(params: String): Flow<DataState<UserDetailDomainModel>> = channelFlow {
      try {
          send(DataState.Loading)
          //Check local database first
          val localResult = localRepo.getUser(login = params)
          if (localResult == null) {
              val remoteResult = remoteRepo.getUserDetailByLogin(params)
              localRepo.insertUser(remoteResult)
              val updatedRemoteResult = isUserFavorite(remoteResult)
              val mappedRemoteResult = mapper.map(updatedRemoteResult)
              send(DataState.Success(mappedRemoteResult))
          } else {
              cacheExpiryDatePolicy.checkExpiry(localResult.createdAt).collectLatest {isDataExpired ->
                  if (isDataExpired) {
                      //Data is expired, fetch updated data from remote api
                      val remoteResult = remoteRepo.getUserDetailByLogin(params)
                      //Check the user is already favorite
                      val updatedRemoteResult = isUserFavorite(remoteResult)
                      val mappedRemoteResult = mapper.map(updatedRemoteResult)
                      send(DataState.Success(mappedRemoteResult))
                  } else {
                      //Data is not expired, use local data
                      val updatedLocalResult = isUserFavorite(localResult)
                      //Check the user is already favorite
                      val mappedLocalResul = mapper.map(updatedLocalResult)
                      send(DataState.Success(mappedLocalResul))
                  }
              }
          }
      } catch (e: Exception) {
          send(DataState.Error(e))
      }
    }

    private fun isUserFavorite(userDetail: UserDetailEntity): UserDetailEntity {
        val favUser = userFavRepo.getUserInfoByLogin(userDetail.login.toString())
        return userDetail.copy(isFavorite = favUser != null)
    }
}