package com.zekierciyas.github_app.feat_userdetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.domain.usecase.UserFavoriteUseCase
import com.zekierciyas.github_app.core.presentation.BaseViewModel
import com.zekierciyas.github_app.feat_userdetail.domain.model.UserDetailDomainModel
import com.zekierciyas.github_app.feat_userdetail.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDetailUseCase: GetUserDetailUseCase,
    private val favUserUseCase: UserFavoriteUseCase
): BaseViewModel() {

    private val _userDetailFlow = MutableStateFlow<DataState<UserDetailDomainModel>>(DataState.Empty)
    val userDetailFlow: StateFlow<DataState<UserDetailDomainModel>> = _userDetailFlow.asStateFlow()

    private var searchJob: Job? = null
    private val login: String? = savedStateHandle["login"]

    fun getUserDetail() {
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            userDetailUseCase
                .execute(login!!)
                .distinctUntilChanged()
                .collect{
                    _userDetailFlow.emit(it)
                }
        }
    }

    fun addUserFavOrRemove() {
        viewModelScope.launch(Dispatchers.IO) {
            favUserUseCase.execute(login!!).collect{
               if (it is DataState.Error) _userDetailFlow.emit(it)
            }
        }
    }
}