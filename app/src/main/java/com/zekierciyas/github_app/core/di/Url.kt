package com.zekierciyas.github_app.core.di

import javax.inject.Inject

class Url @Inject constructor(private val url: String) {
    fun getUrl() = url
}