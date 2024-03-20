package com.zekierciyas.github_app.feat_userdetail.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity

@Database(entities = [UserDetailEntity::class], version = 2, exportSchema = false)
@TypeConverters(UserDetailTypeConverter::class)
abstract class UserDetailCacheDB : RoomDatabase() {
    abstract fun itemUserDetailDao(): UserDetailDao
}
