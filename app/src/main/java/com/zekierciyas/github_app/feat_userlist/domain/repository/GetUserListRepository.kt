package com.zekierciyas.github_app.feat_userlist.domain.repository

import com.zekierciyas.github_app.feat_userlist.data.model.UserResponseEntity

interface GetUserListRepository {
    suspend fun getUserList(query : String): UserResponseEntity
}