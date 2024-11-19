package com.do55anto5.quinto_code.presenter.screens.app.viewmodel

import android.util.Log
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.usecase.image.GetProfilePhotoUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.GetUserUseCase
import com.do55anto5.quinto_code.presenter.screens.app.action.AppAction
import com.do55anto5.quinto_code.presenter.screens.app.state.AppState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getProfilePhotoUseCase: GetProfilePhotoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun submitAction(action: AppAction) {
        when (action) {
            is AppAction.DrawerItemClicked -> handleDrawerItemClick(action.index)
            is AppAction.DrawerStateChanged -> handleDrawerStateChange(action.isOpen)
            is AppAction.NavigateBack -> navigateBack()
            is AppAction.OnLogout -> onLogout()
        }
    }

    init { loadUser() }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val userDeferred = async { getUserUseCase() }
                val photoDeferred = async {
                    try {
                        getProfilePhotoUseCase()
                    } catch (e: Exception) {
                        Log.e("ViewModel", "Error loading photo", e)
                        ""
                    }
                }

                val user = userDeferred.await()
                val photo = photoDeferred.await()

                _state.update { currentState ->
                    currentState.copy(
                        user = User(
                            name = user.name ?: "",
                            surname = user.surname ?: "",
                            photo = photo
                        )
                    )
                }
                stopLoading()
            } catch (exception: Exception) {
                exception.printStackTrace()
                stopLoading()
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    currentDrawerIndex = 0
                )
            }
        }
    }

    private fun handleDrawerItemClick(index: Int) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
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

    private fun onLogout() {
        _state.update { currentState ->
            currentState.copy(isAuthenticated = false)
        }
    }

    private fun startLoading() {
        _state.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
    }

    private fun stopLoading() {
        _state.update { currentState ->
            currentState.copy(
                isLoading = false
            )
        }
    }
}
