package com.zekierciyas.github_app.feat_userlist.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.feat_userlist.data.repository.GetUserListRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRepo: GetUserListRepositoryImp,
    private val userListMapper: UserListMapper
): BaseUseCase<String, DataState<List<UserListDomainModel>>> {
    override suspend fun execute(params: String): Flow<DataState<List<UserListDomainModel>>> = flow {
        try {
            emit(DataState.Loading)
            val list = userListRepo.getUserList(params)
            val mappedList = list.items?.map {
                userListMapper.asDomainModel(it!!)
            }
            emit(DataState.Success(mappedList!!))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}

