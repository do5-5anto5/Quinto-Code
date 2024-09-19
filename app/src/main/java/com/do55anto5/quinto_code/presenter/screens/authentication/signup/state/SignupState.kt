package com.do55anto5.quinto_code.presenter.screens.authentication.signup.state

data class SignupState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val passwordVisibility: Boolean = false,
    val enableSignupButton: Boolean = false,
    val hasFeedBack: Boolean = false,
    val feedBackMessage: Int? = null
)
