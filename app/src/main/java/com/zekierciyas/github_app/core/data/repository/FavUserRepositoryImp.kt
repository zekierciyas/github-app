package com.zekierciyas.github_app.core.data.repository

import com.zekierciyas.github_app.core.data.db.UserInfoDao
import com.zekierciyas.github_app.core.data.db.FavUserEntity
import com.zekierciyas.github_app.core.domain.repository.UserInfoRepository
import javax.inject.Inject

class FavUserRepositoryImp @Inject constructor(
    private val dao: UserInfoDao
): UserInfoRepository {

    override fun getUserInfoByLogin(login: String) : FavUserEntity?{
        return dao.getUserInfo(login = login)
    }

    override fun insertUserInfo(userInfoEntity: FavUserEntity) {
        dao.insertUserInfo(userInfo = userInfoEntity)
    }

    override fun deleteUserInfo(userInfoEntity: FavUserEntity) {
        dao.deleteUserInfo(userInfo = userInfoEntity)
    }
}