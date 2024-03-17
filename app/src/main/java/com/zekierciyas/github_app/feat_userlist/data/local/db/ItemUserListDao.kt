package com.zekierciyas.github_app.feat_userlist.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity

@Dao
interface ItemUserListDao {
    @Query("SELECT * FROM user_list WHERE `query` = :searchQuery")
    fun getItemUserListByQuery(searchQuery: String): UserListEntity

    @Query("DELETE FROM user_list WHERE `query` = :searchQuery")
    fun deleteBySearchQuery(searchQuery: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(itemUserList: UserListEntity)
}
