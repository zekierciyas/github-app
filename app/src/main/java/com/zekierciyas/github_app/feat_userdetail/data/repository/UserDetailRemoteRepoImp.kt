package com.zekierciyas.github_app.feat_userdetail.data.repository

import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userdetail.domain.repository.GetUserDetailRepository
import javax.inject.Inject

class UserDetailRemoteRepoImp @Inject constructor(
    private val githubAPI: GithubAPI
): GetUserDetailRepository {
    override suspend fun getUserDetailByLogin(login: String): UserDetailEntity {
        return githubAPI.getUserDetails(login = login)
    }
}