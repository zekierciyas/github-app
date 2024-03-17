package com.zekierciyas.github_app.feat_userlist.data.local.repository

import com.zekierciyas.github_app.feat_userlist.data.local.db.AppDatabase
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListLocalRepositoryImp @Inject constructor(
    private val itemUserListDao: AppDatabase
) {
    fun getItemUserListByQuery(searchQuery: String): List<ItemUserEntity>? {
        return try {
            val result = itemUserListDao.itemUserListDao().getItemUserListByQuery(searchQuery)
            result.userList
        } catch (e: Exception) {
            null
        }
    }

    fun insertList(searchQuery: String, itemUserList: List<ItemUserEntity>) {
        itemUserListDao.itemUserListDao().insertList(UserListEntity(
            query = searchQuery,
            userList = itemUserList
        ))
    }

    fun deleteBySearchQuery(searchQuery: String) {
        itemUserListDao.itemUserListDao().deleteBySearchQuery(searchQuery)
    }
}
