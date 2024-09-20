package com.do55anto5.quinto_code.presenter.screens.authentication.signup.action

import com.do55anto5.quinto_code.core.enums.input.InputType

sealed class SignupAction {
    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : SignupAction()

    data object OnPasswordVisibilityChange : SignupAction()

    data object OnSignup : SignupAction()

    data object ResetErrorState : SignupAction()

}