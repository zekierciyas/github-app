package com.zekierciyas.github_app.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseURLProvider {

    @Provides
    fun provideBaseURL() = Url("https://api.github.com/")
}