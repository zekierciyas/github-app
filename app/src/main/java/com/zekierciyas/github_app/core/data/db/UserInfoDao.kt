package com.zekierciyas.github_app.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM starred_user WHERE `login` = :login")
    fun getUserInfo(login: String): FavUserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userInfo: FavUserEntity)

    @Delete
    fun deleteUserInfo(userInfo: FavUserEntity)
}
