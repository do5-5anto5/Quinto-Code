package com.do55anto5.quinto_code.presenter.screens.authentication.signup.state

import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType

data class SignupState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val passwordVisibility: Boolean = false,
    val enableSignupButton: Boolean = false,
    val hasFeedBack: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null
)
