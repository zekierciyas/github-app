package com.zekierciyas.github_app.feat_userlist.data.model


import com.google.gson.annotations.SerializedName

data class UserListEntity(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<ItemUserList?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)