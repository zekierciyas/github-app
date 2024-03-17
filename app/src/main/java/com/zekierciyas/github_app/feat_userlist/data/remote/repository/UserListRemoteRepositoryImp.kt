package com.zekierciyas.github_app.feat_userlist.data.remote.repository

import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.feat_userlist.data.model.UserResponseEntity
import com.zekierciyas.github_app.feat_userlist.domain.repository.GetUserListRepository
import javax.inject.Inject

class UserListRemoteRepositoryImp @Inject constructor(
    private val apiService: GithubAPI
): GetUserListRepository {
    override suspend fun getUserList(query: String): UserResponseEntity {
       return apiService.searchUsers(query = query)
    }
}