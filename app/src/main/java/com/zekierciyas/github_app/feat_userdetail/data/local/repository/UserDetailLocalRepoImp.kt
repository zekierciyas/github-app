package com.zekierciyas.github_app.feat_userdetail.data.local.repository

import com.zekierciyas.github_app.core.util.TimeStampManager
import com.zekierciyas.github_app.feat_userdetail.data.local.db.UserDetailDao
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userdetail.domain.repository.UserDetailLocalRepository
import javax.inject.Inject

class UserDetailLocalRepoImp @Inject constructor(
    private val userDetailDao: UserDetailDao,
    private val timePicker: TimeStampManager
) : UserDetailLocalRepository{
    override fun getUser(login: String): UserDetailEntity? {
        return userDetailDao.getUserDetailByLogin(login = login)
    }

    override fun insertUser(userDetailEntity: UserDetailEntity) {
        val userDetailEntityCopy = userDetailEntity.copy()
        userDetailEntity.createdAt = timePicker.getCurrentTime()
        userDetailDao.insertUserDetail(
            userDetailEntity = userDetailEntityCopy
        )
    }
}