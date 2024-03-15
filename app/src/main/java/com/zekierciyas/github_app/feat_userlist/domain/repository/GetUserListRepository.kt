package com.zekierciyas.github_app.feat_userlist.domain.repository

import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import kotlinx.coroutines.flow.Flow

interface GetUserListRepository {
    suspend fun getUserList(query : String): UserListEntity
}