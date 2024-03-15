package com.zekierciyas.github_app.feat_userlist.data.repository

import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import com.zekierciyas.github_app.feat_userlist.domain.repository.GetUserListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListRepositoryImp @Inject constructor(
    private val apiService: GithubAPI
): GetUserListRepository {
    override suspend fun getUserList(query: String): UserListEntity {
       return apiService.searchUsers(query = query)
    }
}