package com.zekierciyas.github_app.feat_userlist.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.feat_userlist.data.local.CacheExpiryDatePolicy
import com.zekierciyas.github_app.feat_userlist.data.local.repository.UserListLocalRepositoryImp
import com.zekierciyas.github_app.feat_userlist.data.remote.repository.UserListRemoteRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRemoteRepo: UserListRemoteRepositoryImp,
    private val userListLocalRepo: UserListLocalRepositoryImp,
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
                val mappedList = list.items?.map {
                    userListMapper.map(it)
                }
                userListLocalRepo.insertList(searchQuery = params, list.items!! )
                send(DataState.Success(mappedList!!))

            } else {
                cacheExpiryDatePolicy.checkExpiry(localResult.createdAt).collectLatest{ isExpired ->
                    if (isExpired) {
                        //Data is expired, fetch updated data from remote api
                        val list = userListRemoteRepo.getUserList(params)
                        val mappedList = list.items?.map {
                            userListMapper.map(it)
                        }
                        userListLocalRepo.insertList(searchQuery = params, list.items!! )
                        send(DataState.Success(mappedList!!))

                    } else {
                        //Data is not expired, read from local database
                        val mappedList = localResult.userList.map {
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
}

