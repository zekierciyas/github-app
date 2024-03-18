package com.zekierciyas.github_app.feat_userdetail.domain.repository

import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import kotlinx.coroutines.flow.Flow

interface GetUserDetailRepository {
    suspend fun getUserDetailByLogin(login: String): UserDetailEntity
}