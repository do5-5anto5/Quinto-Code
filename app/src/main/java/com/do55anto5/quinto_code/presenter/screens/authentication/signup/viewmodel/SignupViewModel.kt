package com.do55anto5.quinto_code.presenter.screens.authentication.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.input.InputType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.util.isValidEmail
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.RegisterUseCase
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.state.SignupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun submitAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            SignupAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }

            SignupAction.OnSignup -> {
                onSignup()
            }

            SignupAction.ResetErrorState -> {
                resetErrorState()
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
        enableSignupButton()
    }

    private fun onPasswordChange(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value)
        }
    }

    private fun onEmailChange(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun onPasswordVisibilityChange() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun enableSignupButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enableSignupButton = emailValid && passwordValid)
        }
    }

    private fun onSignup() {
        viewModelScope.launch {
            try {
                _state.update { currentState ->
                    currentState.copy(isLoading = true)
                }
                registerUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )
                _state.update { currentState ->
                    currentState.copy(
                        isAuthenticated = true,
                        isLoading = false
                    )
                }
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update { currentState ->
                    currentState.copy(
                        hasFeedBack = true,
                        feedbackUI = Pair(
                            FeedbackType.ERROR,
                            FirebaseHelper.validateError(exception.message)
                        ),
                        isLoading = false
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
