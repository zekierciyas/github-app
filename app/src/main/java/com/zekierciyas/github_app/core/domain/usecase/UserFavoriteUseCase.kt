package com.zekierciyas.github_app.core.domain.usecase

import com.zekierciyas.github_app.core.data.db.FavUserEntity
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.data.repository.FavUserRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Common UseCase to add user information [FavUserEntity] data to AppDatabase
 */
class UserFavoriteUseCase @Inject constructor(
    private val userInfoRepository: FavUserRepositoryImp
): BaseUseCase<String, DataState<String>> {
    override suspend fun execute(params: String): Flow<DataState<String>> = flow{
        try {
            emit(DataState.Loading)
            val favoriteUser = userInfoRepository.getUserInfoByLogin(params)
            val alreadyAddedToFav = favoriteUser != null
            if (alreadyAddedToFav) {
                userInfoRepository.deleteUserInfo(favoriteUser!!)
            } else {
                userInfoRepository.insertUserInfo(FavUserEntity(login = params))
            }
            emit(DataState.Success(params))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}