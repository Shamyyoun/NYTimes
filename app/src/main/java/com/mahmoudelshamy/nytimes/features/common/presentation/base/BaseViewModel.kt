package com.mahmoudelshamy.nytimes.features.common.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.mahmoudelshamy.nytimes.features.common.presentation.NavigationCommand
import com.mahmoudelshamy.nytimes.utils.SingleLiveEvent

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