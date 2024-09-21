package com.do55anto5.quinto_code.presenter.screens.authentication.login.action

import com.do55anto5.quinto_code.core.enums.input.InputType

sealed class LoginAction {

    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : LoginAction()

    data object OnPasswordVisibilityChange: LoginAction()

    data object OnSignIn: LoginAction()

    data object ResetErrorState: LoginAction()

}