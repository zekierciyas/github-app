package com.zekierciyas.github_app.core.data.model

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()

    data object Empty : DataState<Nothing>()

}