package com.zekierciyas.github_app.core.domain.usecase

import com.zekierciyas.github_app.core.data.db.FavUserEntity
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

/**
 * Common UseCase to remove user information [FavUserEntity] data from AppDatabase
 */
class RemoveUserFromFavoriteUseCase@Inject constructor(
    private val userInfoRepository: FavUserRepositoryImp
): BaseUseCase<String, DataState<String>> {
    override suspend fun execute(params: String): Flow<DataState<String>> = channelFlow{
        try {
            send(DataState.Loading)
            userInfoRepository.deleteUserInfo(FavUserEntity(login = params))
            send(DataState.Success(params))
        } catch (e: Exception) {
            send(DataState.Error(e))
        }
    }
}