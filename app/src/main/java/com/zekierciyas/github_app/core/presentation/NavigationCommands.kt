package com.zekierciyas.github_app.core.presentation

import androidx.navigation.NavDirections

sealed class NavigationCommands {
    data class NavigateDirections(val direction: NavDirections) : NavigationCommands()
    data object NavigateToHomeScreen : NavigationCommands()
}