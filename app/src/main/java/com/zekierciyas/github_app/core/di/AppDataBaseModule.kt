package com.zekierciyas.github_app.core.di

import android.content.Context
import androidx.room.Room
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import com.zekierciyas.github_app.core.data.db.AppDatabase
import com.zekierciyas.github_app.core.data.db.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataBaseModule {

    @Provides
    @Singleton
    fun provideSampleRepository(userInfoDao: UserInfoDao): FavUserRepositoryImp {
        return FavUserRepositoryImp(userInfoDao)
    }

    @Provides
    @Singleton
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-db"
        ).build()
    }
}