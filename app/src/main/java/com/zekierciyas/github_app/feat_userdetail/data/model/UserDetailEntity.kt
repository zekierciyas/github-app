package com.zekierciyas.github_app.feat_userdetail.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @ColumnInfo(name = "bio")
    @SerializedName("bio")
    val bio: String?,

    @ColumnInfo(name = "blog")
    @SerializedName("blog")
    val blog: String?,

    @ColumnInfo(name = "company")
    @SerializedName("company")
    val company: String?,

    @ColumnInfo(name = "created_at_on_db")
    @SerializedName("created_at_on_db")
    var createdAt: String? = "",

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String?,

    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    val eventsUrl: String?,

    @ColumnInfo(name = "followers")
    @SerializedName("followers")
    val followers: Int?,

    @ColumnInfo(name = "followers_url")
    @SerializedName("followers_url")
    val followersUrl: String?,

    @ColumnInfo(name = "following")
    @SerializedName("following")
    val following: Int?,

    @ColumnInfo(name = "following_url")
    @SerializedName("following_url")
    val followingUrl: String?,

    @ColumnInfo(name = "gists_url")
    @SerializedName("gists_url")
    val gistsUrl: String?,

    @ColumnInfo(name = "gravatar_id")
    @SerializedName("gravatar_id")
    val gravatarId: String?,

    @ColumnInfo(name = "hireable")
    @SerializedName("hireable")
    val hireable: Boolean?,

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    val htmlUrl: String?,

    @ColumnInfo(name = "location")
    @SerializedName("location")
    val location: String?,

    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    val nodeId: String?,

    @ColumnInfo(name = "organizations_url")
    @SerializedName("organizations_url")
    val organizationsUrl: String?,

    @ColumnInfo(name = "public_gists")
    @SerializedName("public_gists")
    val publicGists: Int?,

    @ColumnInfo(name = "public_repos")
    @SerializedName("public_repos")
    val publicRepos: Int?,

    @ColumnInfo(name = "received_events_url")
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    val reposUrl: String?,

    @ColumnInfo(name = "site_admin")
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,

    @ColumnInfo(name = "starred_url")
    @SerializedName("starred_url")
    val starredUrl: String?,

    @ColumnInfo(name = "subscriptions_url")
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,

    @ColumnInfo(name = "twitter_username")
    @SerializedName("twitter_username")
    val twitterUsername: String?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,

    @SerializedName("is_favorite")
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)
