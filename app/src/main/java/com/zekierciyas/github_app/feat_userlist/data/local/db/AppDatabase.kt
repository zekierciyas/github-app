package com.zekierciyas.github_app.feat_userlist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListTypeConverter

@Database(entities = [UserListEntity::class], version = 13, exportSchema = false)
@TypeConverters(UserListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemUserListDao(): ItemUserListDao
}
