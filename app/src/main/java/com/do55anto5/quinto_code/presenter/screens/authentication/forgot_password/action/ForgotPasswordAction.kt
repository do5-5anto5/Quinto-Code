package com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action

import com.do55anto5.quinto_code.core.enums.input.InputType


sealed class ForgotPasswordAction {
    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : ForgotPasswordAction()

    data object OnSend: ForgotPasswordAction()

    data object ResetErrorState: ForgotPasswordAction()
}