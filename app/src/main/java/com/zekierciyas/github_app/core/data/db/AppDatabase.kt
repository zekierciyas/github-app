package com.zekierciyas.github_app.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavUserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}
