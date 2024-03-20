package com.zekierciyas.github_app.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private var _navigateTo = MutableSharedFlow<NavigationCommands>(replay = 0, extraBufferCapacity = 1, BufferOverflow.DROP_OLDEST)
    val navigateTo: MutableSharedFlow<NavigationCommands> = _navigateTo

    fun navigateTo(action: NavDirections) {
        viewModelScope.launch {
            _navigateTo.emit(NavigationCommands.NavigateDirections(action))
        }
    }

    fun navigateToHomeScreen() {
        viewModelScope.launch {
            _navigateTo.emit(NavigationCommands.NavigateToHomeScreen)
        }
    }
}

