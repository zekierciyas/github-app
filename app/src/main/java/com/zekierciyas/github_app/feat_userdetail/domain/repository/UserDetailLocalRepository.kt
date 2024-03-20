package com.zekierciyas.github_app.feat_userdetail.domain.repository

import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity

interface UserDetailLocalRepository {
    fun getUser(login: String): UserDetailEntity?

    fun insertUser(userDetailEntity: UserDetailEntity)
}