package com.mahmoudelshamy.nytimes.common

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ToDirection(val direction: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}