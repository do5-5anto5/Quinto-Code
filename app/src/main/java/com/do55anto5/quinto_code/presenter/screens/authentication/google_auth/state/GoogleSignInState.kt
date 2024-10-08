package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state

import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType

sealed class GoogleSignInState {
    data object Idle : GoogleSignInState()
    data object Loading : GoogleSignInState()
    data class Success(val message: String) : GoogleSignInState()
    data class IsAuthenticated(val isAuthenticated: Boolean = true) : GoogleSignInState()
    data class Error(
        val hasFeedback: Boolean = false,
        val feedbackUI: Pair<FeedbackType, Int>? = null
    ) : GoogleSignInState()
}