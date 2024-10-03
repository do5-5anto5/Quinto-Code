package com.do55anto5.quinto_code.presenter.screens.authentication.login.state

import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val enableSignInButton: Boolean = false,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val isAuthenticated: Boolean = false
)