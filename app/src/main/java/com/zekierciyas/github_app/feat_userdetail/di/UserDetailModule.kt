package com.zekierciyas.github_app.feat_userdetail.di

import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.feat_userdetail.data.repository.UserDetailRepositoryImp
import com.zekierciyas.github_app.feat_userdetail.domain.mapper.UserDetailMapper
import com.zekierciyas.github_app.feat_userdetail.domain.usecase.GetUserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDetailModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: UserDetailRepositoryImp, mapper: UserDetailMapper): GetUserDetailUseCase {
        return GetUserDetailUseCase(
            repository = repository,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideSampleRepository(apiService: GithubAPI): UserDetailRepositoryImp {
        return UserDetailRepositoryImp(githubAPI = apiService)
    }

    @Provides
    @Singleton
    fun provideMapper(): UserDetailMapper {
        return UserDetailMapper()
    }
}