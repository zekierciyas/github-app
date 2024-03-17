package com.zekierciyas.github_app.feat_userlist.data.model


import com.google.gson.annotations.SerializedName

data class UserResponseEntity(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<ItemUserEntity>?,
    @SerializedName("total_count")
    val totalCount: Int?
)