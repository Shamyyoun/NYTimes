package com.mahmoudelshamy.nytimes.features.common.presentation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirection(val direction: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}