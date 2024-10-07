package com.do55anto5.quinto_code.presenter.screens.app.viewmodel

import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.presenter.screens.app.action.AppAction
import com.do55anto5.quinto_code.presenter.screens.app.state.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun submitAction(action: AppAction) {
        when (action) {
            is AppAction.DrawerItemClicked -> handleDrawerItemClick(action.index)
            is AppAction.DrawerStateChanged -> handleDrawerStateChange(action.isOpen)
            is AppAction.NavigateBack -> handleNavigateBack()
            is AppAction.OnLogout -> onLogout()
        }
    }

    private fun handleDrawerItemClick(index: Int) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    previousDrawerIndex = currentState.currentDrawerIndex,
                    currentDrawerIndex = index,
                    drawerStateValue = DrawerValue.Closed
                )
            }
        }
    }

    private fun handleDrawerStateChange(isOpen: Boolean) {
        _state.update {
            it.copy(drawerStateValue = if (isOpen) DrawerValue.Open else DrawerValue.Closed)
        }
    }

    private fun handleNavigateBack() {
        // Implement
    }

    private fun onLogout() {
        _state.update { currentState ->
            currentState.copy(isAuthenticated = false)
        }
    }
}
