package com.zekierciyas.github_app.feat_userlist.domain.repository

import com.zekierciyas.github_app.feat_userlist.data.model.ItemUserEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity

interface UserListLocalRepository {
    fun getItemUserListByQuery(searchQuery: String): UserListEntity?

    fun insertList(searchQuery: String, itemUserList: List<ItemUserEntity>)
}