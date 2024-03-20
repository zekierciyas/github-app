package com.zekierciyas.github_app.feat_userlist.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.core.data.db.CacheExpiryDatePolicy
import com.zekierciyas.github_app.feat_userlist.data.local.repository.UserListLocalRepositoryImp
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.data.remote.repository.UserListRemoteRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRemoteRepo: UserListRemoteRepositoryImp,
    private val userListLocalRepo: UserListLocalRepositoryImp,
    private val userFavRepo: FavUserRepositoryImp,
    private val userListMapper: UserListMapper,
    private val cacheExpiryDatePolicy: CacheExpiryDatePolicy
): BaseUseCase<String, DataState<List<UserListDomainModel>>> {
    override suspend fun execute(params: String): Flow<DataState<List<UserListDomainModel>>> = channelFlow {
        if (params.isBlank()) return@channelFlow
        try {
            send(DataState.Loading)
            //Check the local database first
            val localResult = userListLocalRepo.getItemUserListByQuery(searchQuery = params)
            if (localResult == null) {
                val list = userListRemoteRepo.getUserList(params)
                val userList = searchFavorites(list.items!!)
                val mappedList = userList.map {
                    userListMapper.map(it)
                }
                userListLocalRepo.insertList(searchQuery = params, list.items)
                send(DataState.Success(mappedList))

            } else {
                cacheExpiryDatePolicy.checkExpiry(localResult.createdAt).collectLatest{ isExpired ->
                    if (isExpired) {
                        //Data is expired, fetch updated data from remote api
                        val list = userListRemoteRepo.getUserList(params)
                        //Check the users which one is favorite
                        val userList = searchFavorites(list.items!!)
                        val mappedList = userList.map {
                            userListMapper.map(it)
                        }
                        userListLocalRepo.insertList(searchQuery = params, list.items)
                        send(DataState.Success(mappedList))

                    } else {
                        //Data is not expired, read from local database
                        val userList = searchFavorites(localResult.userList)
                        val mappedList = userList.map {
                            userListMapper.map(it)
                        }
                        send(DataState.Success(mappedList))
                    }
                }
            }
        } catch (e: Exception) {
            send(DataState.Error(e))
        }
    }

    private fun searchFavorites(list: List<ItemUserEntity>): List<ItemUserEntity> {
        val updatedList = list.toMutableList()
        list.forEachIndexed { index, item->
           val favUser = userFavRepo.getUserInfoByLogin(item.login.toString())
            updatedList[index].isFavorite = favUser != null
        }
        return updatedList
    }
}

