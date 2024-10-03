package com.do55anto5.quinto_code.presenter.screens.authentication.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.input.InputType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.util.isValidEmail
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.LoginUseCase
import com.do55anto5.quinto_code.presenter.screens.authentication.login.action.LoginAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.do55anto5.quinto_code.presenter.screens.authentication.login.state.LoginState

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun submitAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is LoginAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }

            is LoginAction.OnSignIn -> {
                onSignIn()
            }

            is LoginAction.ResetErrorState -> {
                resetErrorState()
            }
        }
    }

    private fun onSignIn() {
        viewModelScope.launch {
            try {
                loginUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )
                _state.update { currentState ->
                    currentState.copy(isAuthenticated = true)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update { currentState ->
                    currentState.copy(
                        hasFeedback = true,
                        feedbackUI = Pair(
                            FeedbackType.ERROR,
                            FirebaseHelper.validateError(exception.message)
                        )
                    )
                }
            }
        }
    }

    private fun onValueChange(value: String, type: InputType) {
        when (type) {
            InputType.EMAIL -> {
                onEmailChange(value)
            }

            InputType.PASSWORD -> {
                onPasswordChange(value)
            }
        }

        enableSignInButton()
    }

    private fun onEmailChange(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun onPasswordChange(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value)
        }
    }

    private fun onPasswordVisibilityChange() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun enableSignInButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enableSignInButton = emailValid && passwordValid)
        }
    }

    private fun resetErrorState() {
        _state.update { currentState ->
            currentState.copy(
                hasFeedback = false,
                feedbackUI = null
            )
        }
    }

}