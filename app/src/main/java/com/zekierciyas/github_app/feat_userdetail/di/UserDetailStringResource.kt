package com.zekierciyas.github_app.feat_userdetail.di

import android.content.Context
import com.zekierciyas.github_app.R
import javax.inject.Inject

class UserDetailStringResource @Inject constructor(
    private val context: Context
) {
    fun getUserFetchErrorMessage() = context.getString(R.string.app_name)
}