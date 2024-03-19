package com.zekierciyas.github_app.feat_userlist.domain.model


import com.google.gson.annotations.SerializedName

data class UserListDomainModel(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false
)
