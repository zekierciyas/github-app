package com.zekierciyas.github_app.feat_userlist.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.feat_userlist.data.local.repository.UserListLocalRepositoryImp
import com.zekierciyas.github_app.feat_userlist.data.remote.repository.UserListRemoteRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRemoteRepo: UserListRemoteRepositoryImp,
    private val userListLocalRepo: UserListLocalRepositoryImp,
    private val userListMapper: UserListMapper
): BaseUseCase<String, DataState<List<UserListDomainModel>>> {
    override suspend fun execute(params: String): Flow<DataState<List<UserListDomainModel>>> = flow {
        try {
            emit(DataState.Loading)
            //Check the local
            val localResult = userListLocalRepo.getItemUserListByQuery(searchQuery = params)
            if (localResult == null) {
                val list = userListRemoteRepo.getUserList(params)
                val mappedList = list.items?.map {
                    userListMapper.map(it!!)
                }
                userListLocalRepo.insertList(searchQuery = params, list.items!! )
                emit(DataState.Success(mappedList!!))

            } else {
                val mappedList = localResult.map {
                    userListMapper.map(it)
                }
                emit(DataState.Success(mappedList))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}

