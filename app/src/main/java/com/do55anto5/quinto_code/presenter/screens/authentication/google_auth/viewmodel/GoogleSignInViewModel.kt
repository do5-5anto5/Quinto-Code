package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.GoogleSignInUseCase
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.action.GoogleSignInAction
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state.GoogleSignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GoogleSignInViewModel(
    private val useCase: GoogleSignInUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<GoogleSignInState>(GoogleSignInState.Idle)
    val state: StateFlow<GoogleSignInState> = _state.asStateFlow()

    fun submitAction(action: GoogleSignInAction) {
        when (action) {
            is GoogleSignInAction.SignIn -> signIn(action.context)
        }
    }

    private fun signIn(context: Context) {
        viewModelScope.launch {
            _state.value = GoogleSignInState.Loading
            try {
                useCase(context).fold(
                    onSuccess = {
                        _state.value = GoogleSignInState.Success("Successfully signed in")
                    },
                    onFailure = { exception ->
                        _state.value = GoogleSignInState.Error(exception.message ?: "Unknown error")
                    }
                )
            } catch (e: Exception) {
                _state.value = GoogleSignInState.Error(e.message ?: "Unknown error")
            }
        }
    }
}