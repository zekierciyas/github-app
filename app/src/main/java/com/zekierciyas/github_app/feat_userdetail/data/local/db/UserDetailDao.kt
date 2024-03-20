package com.zekierciyas.github_app.feat_userdetail.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity

@Dao
interface UserDetailDao {
    @Query("SELECT * FROM user_detail WHERE `login` = :login")
    fun getUserDetailByLogin(login: String): UserDetailEntity

    @Query("DELETE FROM user_detail WHERE `login` = :login")
    fun deleteUserDetailByLogin(login: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetail(userDetailEntity: UserDetailEntity)
}
