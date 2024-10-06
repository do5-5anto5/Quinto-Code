package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state

sealed class GoogleSignInState {
    data object Idle : GoogleSignInState()
    data object Loading : GoogleSignInState()
    data class Success(val message: String) : GoogleSignInState()
    data class Error(val message: String) : GoogleSignInState()
}