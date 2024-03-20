package com.zekierciyas.github_app.feat_userdetail.data.local.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import java.util.Date
import javax.inject.Inject
@ProvidedTypeConverter
class UserDetailTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromUserDetailEntity(userDetailEntity: UserDetailEntity?): String? {
        return userDetailEntity?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toUserDetailEntity(userDetailEntityString: String?): UserDetailEntity? {
        return userDetailEntityString?.let {
            gson.fromJson(userDetailEntityString, UserDetailEntity::class.java)
        }
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}