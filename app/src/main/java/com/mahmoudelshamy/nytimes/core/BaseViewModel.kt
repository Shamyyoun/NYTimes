package com.mahmoudelshamy.nytimes.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.mahmoudelshamy.nytimes.common.NavigationCommand
import com.mahmoudelshamy.nytimes.common.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    private val _navigation = SingleLiveEvent<NavigationCommand>()
    val navigation: LiveData<NavigationCommand> get() = _navigation

    fun navigate(navDirections: NavDirections) {
        _navigation.value = NavigationCommand.ToDirection(navDirections)
    }

    fun navigateBack() {
        _navigation.value = NavigationCommand.Back
    }
}