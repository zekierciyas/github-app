package com.zekierciyas.github_app.feat_userlist.di

import android.content.Context
import androidx.room.Room
import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import com.zekierciyas.github_app.feat_userlist.data.local.CacheExpiryDatePolicy
import com.zekierciyas.github_app.feat_userlist.data.local.db.UserListCacheDB
import com.zekierciyas.github_app.feat_userlist.data.local.repository.UserListLocalRepositoryImp
import com.zekierciyas.github_app.feat_userlist.data.remote.repository.UserListRemoteRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.usecase.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserListModule {

    @Provides
    @Singleton
    fun provideSampleRepository(apiService: GithubAPI): UserListRemoteRepositoryImp {
        return UserListRemoteRepositoryImp(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideSampleUseCase(
        repository: UserListRemoteRepositoryImp,
        userListLocalRepositoryImp: UserListLocalRepositoryImp,
        userFavRepo: FavUserRepositoryImp,
        userListMapper: UserListMapper,
        cacheExpiryDatePolicy: CacheExpiryDatePolicy
    ): GetUserListUseCase {
        return GetUserListUseCase(
            userListRemoteRepo = repository,
            userListLocalRepo = userListLocalRepositoryImp,
            userListMapper = userListMapper,
            cacheExpiryDatePolicy = cacheExpiryDatePolicy,
            userFavRepo = userFavRepo
        )
    }

    @Provides
    @Singleton
    fun provideUserListMapper(): UserListMapper {
        return UserListMapper()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): UserListCacheDB {
        return Room.databaseBuilder(
            context,
            UserListCacheDB::class.java,
            "github-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemUserListRepository(db: UserListCacheDB): UserListLocalRepositoryImp {
        return UserListLocalRepositoryImp(db)
    }

    @Provides
    @Singleton
    fun cacheExpiryPolicy(): CacheExpiryDatePolicy {
        return CacheExpiryDatePolicy(calendar = Calendar.getInstance())
    }
}