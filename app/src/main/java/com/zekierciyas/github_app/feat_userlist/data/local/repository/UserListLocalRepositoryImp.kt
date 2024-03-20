package com.zekierciyas.github_app.feat_userlist.data.local.repository

import com.zekierciyas.github_app.feat_userlist.data.local.db.UserListCacheDB
import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import com.zekierciyas.github_app.feat_userlist.domain.repository.UserListLocalRepository
import java.util.Calendar
import javax.inject.Inject

class UserListLocalRepositoryImp @Inject constructor(
    private val itemUserListDao: UserListCacheDB
): UserListLocalRepository {
   override fun getItemUserListByQuery(searchQuery: String): UserListEntity? {
        return try {
            return itemUserListDao.itemUserListDao().getItemUserListByQuery(searchQuery)
        } catch (e: Exception) {
            null
        }
    }

    override fun insertList(searchQuery: String, itemUserList: List<ItemUserEntity>) {
        itemUserListDao.itemUserListDao().insertList(UserListEntity(
            query = searchQuery,
            userList = itemUserList,
            createdAt = Calendar.getInstance().time.time
        ))
    }
}
