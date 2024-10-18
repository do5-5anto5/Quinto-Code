package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state

import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType

data class GoogleSignInState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null
)
