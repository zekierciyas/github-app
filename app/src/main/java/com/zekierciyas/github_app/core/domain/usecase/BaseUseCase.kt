package com.zekierciyas.github_app.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Params, out Result> {

    /**
     * Executes the use case asynchronously.
     *
     * @param params Parameters required by the use case.
     * @return A [Flow] emitting the result of the use case.
     */
    suspend fun execute(params: Params): Flow<Result>
}