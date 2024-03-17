package com.zekierciyas.github_app.feat_userlist.presentation

import androidx.lifecycle.viewModelScope
import com.zekierciyas.github_app.core.coroutine.WhileSubscribedOrRetained
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.presentation.BaseViewModel
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel
import com.zekierciyas.github_app.feat_userlist.domain.usecase.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListUseCase: GetUserListUseCase
) : BaseViewModel() {

    private val _userFlow = MutableStateFlow<DataState<List<UserListDomainModel>>>(DataState.Loading)
    val userFlow: StateFlow<DataState<List<UserListDomainModel>>> = _userFlow.asStateFlow()

    private var searchJob: Job? = null

    @OptIn(FlowPreview::class)
    fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            userListUseCase
                .execute(query)
                .distinctUntilChanged()
                .debounce(500)
                .collect{
                    _userFlow.emit(it)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel all active coroutines, including StateFlow observations
        viewModelScope.cancel()
    }
}