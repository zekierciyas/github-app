package com.zekierciyas.github_app.feat_userlist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_list")
data class UserListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "user_list") val userList: List<ItemUserEntity>,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "created_at") val createdAt: String? = "")
data class ItemUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @SerializedName("events_url")
    @ColumnInfo(name = "events_url")
    val eventsUrl: String?,

    @SerializedName("followers_url")
    @ColumnInfo(name = "followers_url")
    val followersUrl: String?,

    @SerializedName("following_url")
    @ColumnInfo(name = "following_url")
    val followingUrl: String?,

    @SerializedName("gists_url")
    @ColumnInfo(name = "gists_url")
    val gistsUrl: String?,

    @SerializedName("gravatar_id")
    @ColumnInfo(name = "gravatar_id")
    val gravatarId: String?,

    @SerializedName("html_url")
    @ColumnInfo(name = "html_url")
    val htmlUrl: String?,

    @SerializedName("login")
    @ColumnInfo(name = "login")
    val login: String?,

    @SerializedName("node_id")
    @ColumnInfo(name = "node_id")
    val nodeId: String?,

    @SerializedName("organizations_url")
    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String?,

    @SerializedName("received_events_url")
    @ColumnInfo(name = "received_events_url")
    val receivedEventsUrl: String?,

    @SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    val reposUrl: String?,

    @SerializedName("score")
    @ColumnInfo(name = "score")
    val score: Double?,

    @SerializedName("site_admin")
    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean?,

    @SerializedName("starred_url")
    @ColumnInfo(name = "starred_url")
    val starredUrl: String?,

    @SerializedName("subscriptions_url")
    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsUrl: String?,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String?,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,

    @SerializedName("is_favorite")
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)

class UserListTypeConverter {

    @TypeConverter
    fun listToJson(value: List<ItemUserEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ItemUserEntity>::class.java).toList()
}



