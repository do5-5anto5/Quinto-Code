package com.do55anto5.quinto_code.presenter.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.preferences.AppPreferences
import com.do55anto5.quinto_code.presenter.screens.splash.action.SplashAction
import com.do55anto5.quinto_code.presenter.screens.splash.state.SplashState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    fun submitAction(action: SplashAction) {
        when (action) {
            SplashAction.OnNextScreen -> {
                getWelcomeVisited()
            }
        }
    }

    private fun getWelcomeVisited() {
        _state.update { currentState ->
            currentState.copy(
                isWelcomeVisited = appPreferences.getWelcomeVisited(),
                isLoading = false,
                isAuthenticated = FirebaseHelper.isAuthenticated()
            )
        }
    }

}