package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.action

import android.content.Context

sealed class GoogleSignInAction {
    data class SignIn(val context: Context) : GoogleSignInAction()
    data object ResetErrorState: GoogleSignInAction()
}