package com.zekierciyas.github_app.feat_userlist.di

import com.zekierciyas.github_app.core.data.api.GithubAPI
import com.zekierciyas.github_app.feat_userlist.data.repository.GetUserListRepositoryImp
import com.zekierciyas.github_app.feat_userlist.domain.mapper.UserListMapper
import com.zekierciyas.github_app.feat_userlist.domain.usecase.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserListModule {

    @Provides
    @ViewModelScoped
    fun provideSampleRepository(apiService: GithubAPI): GetUserListRepositoryImp {
        return GetUserListRepositoryImp(apiService = apiService)
    }

    @Provides
    @ViewModelScoped
    fun provideSampleUseCase(repository: GetUserListRepositoryImp, userListMapper: UserListMapper): GetUserListUseCase {
        return GetUserListUseCase(userListRepo = repository, userListMapper = userListMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideUserListMapper(): UserListMapper {
        return UserListMapper()
    }
}