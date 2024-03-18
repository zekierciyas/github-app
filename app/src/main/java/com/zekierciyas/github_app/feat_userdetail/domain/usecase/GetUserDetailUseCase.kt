package com.zekierciyas.github_app.feat_userdetail.domain.usecase

import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.domain.usecase.BaseUseCase
import com.zekierciyas.github_app.feat_userdetail.data.repository.UserDetailRepositoryImp
import com.zekierciyas.github_app.feat_userdetail.domain.mapper.UserDetailMapper
import com.zekierciyas.github_app.feat_userdetail.domain.model.UserDetailDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserDetailRepositoryImp,
    private val mapper: UserDetailMapper
): BaseUseCase<String, DataState<UserDetailDomainModel>> {
    override suspend fun execute(params: String): Flow<DataState<UserDetailDomainModel>> = channelFlow {
      try {
          send(DataState.Loading)
          val result = repository.getUserDetailByLogin(params)
          val mappedResult = mapper.map(result)
          send(DataState.Success(mappedResult))
      } catch (e: Exception) {
          send(DataState.Error(e))
      }
    }
}