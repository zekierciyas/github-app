package com.zekierciyas.github_app.core.domain.repository

import com.zekierciyas.github_app.core.data.db.FavUserEntity

interface UserInfoRepository {

    fun getUserInfoByLogin(login: String): FavUserEntity?

    fun insertUserInfo(userInfoEntity: FavUserEntity)

    fun deleteUserInfo(userInfoEntity: FavUserEntity)
}