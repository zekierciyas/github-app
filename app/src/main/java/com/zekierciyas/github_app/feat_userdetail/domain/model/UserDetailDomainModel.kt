package com.zekierciyas.github_app.feat_userdetail.domain.model

import com.google.gson.annotations.SerializedName

data class UserDetailDomainModel (
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("bio")
    val bio: Any?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("email")
    val email: Any?,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean? = false
)