package com.zekierciyas.github_app.feat_userdetail.di

import android.content.Context
import androidx.room.Room
import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import com.zekierciyas.github_app.core.util.TimeStampManager
import com.zekierciyas.github_app.feat_userdetail.data.local.db.UserDetailCacheDB
import com.zekierciyas.github_app.feat_userdetail.data.local.db.UserDetailDao
import com.zekierciyas.github_app.feat_userdetail.data.local.repository.UserDetailLocalRepoImp
import com.zekierciyas.github_app.feat_userdetail.data.repository.UserDetailRemoteRepoImp
import com.zekierciyas.github_app.feat_userdetail.domain.mapper.UserDetailMapper
import com.zekierciyas.github_app.feat_userdetail.domain.usecase.GetUserDetailUseCase
import com.zekierciyas.github_app.core.data.db.CacheExpiryDatePolicy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDetailModule {

    @Provides
    @Singleton
    fun provideUseCase(
        remoteRepo: UserDetailRemoteRepoImp,
        localRepo: UserDetailLocalRepoImp,
        userFavRepo: FavUserRepositoryImp,
        cacheExpiryDatePolicy: CacheExpiryDatePolicy,
        mapper: UserDetailMapper

    ): GetUserDetailUseCase {
        return GetUserDetailUseCase(
            remoteRepo = remoteRepo,
            localRepo = localRepo,
            userFavRepo = userFavRepo,
            cacheExpiryDatePolicy = cacheExpiryDatePolicy,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideRemoteUserDetailRepository(apiService: GithubAPI): UserDetailRemoteRepoImp {
        return UserDetailRemoteRepoImp(githubAPI = apiService)
    }

    @Provides
    @Singleton
    fun provideLocalUserDetailRepository(userDetailDao: UserDetailDao, currentTimePicker: TimeStampManager): UserDetailLocalRepoImp {
        return UserDetailLocalRepoImp(userDetailDao = userDetailDao, timePicker =  currentTimePicker)
    }

    @Provides
    @Singleton
    fun userDetailDao(userDetailDB: UserDetailCacheDB): UserDetailDao {
        return userDetailDB.itemUserDetailDao()
    }

    @Provides
    @Singleton
    fun provideUserDetailDatabase(@ApplicationContext context: Context): UserDetailCacheDB {
        return Room.databaseBuilder(
            context,
            UserDetailCacheDB::class.java,
            "user-detail-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMapper(): UserDetailMapper {
        return UserDetailMapper()
    }
}