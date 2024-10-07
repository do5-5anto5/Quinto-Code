package com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.input.InputType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.util.isValidEmail
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.ForgotPasswordUseCase
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action.ForgotPasswordAction
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.state.ForgotPasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    val state = _state.asStateFlow()

    fun submitAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }
            is ForgotPasswordAction.OnSend -> {
                onSend()
            }
            is ForgotPasswordAction.ResetErrorState -> {
                resetErrorState()
            }
        }
    }

    private fun onValueChange(value: String, type: InputType) {
        when (type) {
            InputType.EMAIL -> {
                onEmailChange(value)
            }
            InputType.PASSWORD -> {}
        }
        enableSendButton()
    }

    private fun onEmailChange(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun enableSendButton() {
        val emailValid = isValidEmail(_state.value.email)

        _state.update { currentState ->
            currentState.copy(enableSendButton = emailValid)
        }
    }

    private fun onSend() {
        viewModelScope.launch {
            try {
                forgotPasswordUseCase(
                    email = _state.value.email
                )
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update { currentState ->
                    currentState.copy(
                        hasFeedBack = true,
                        feedbackUI = Pair(
                            FeedbackType.ERROR,
                            FirebaseHelper.validateError(exception.message)
                        )
                    )
                }
            }
        }
    }

    private fun resetErrorState() {
        _state.update { currentState ->
            currentState.copy(
                hasFeedBack = false,
                feedbackUI = null
            )
        }
    }

}
