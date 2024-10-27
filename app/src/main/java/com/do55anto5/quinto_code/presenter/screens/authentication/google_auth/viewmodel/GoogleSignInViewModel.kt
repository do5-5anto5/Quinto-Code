package com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.custom_errors.AuthError
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.GoogleSignInUseCase
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.action.GoogleSignInAction
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state.GoogleSignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoogleSignInViewModel(
    private val useCase: GoogleSignInUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GoogleSignInState())
    val state = _state.asStateFlow()

    fun submitAction(action: GoogleSignInAction) {
        when (action) {
            is GoogleSignInAction.SignIn -> signIn(action.context)
            is GoogleSignInAction.ResetErrorState -> resetErrorState()
        }
    }

    private fun signIn(context: Context) {
        viewModelScope.launch {
            try {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = true
                    )
                }
                useCase(context).fold(
                    onSuccess = {
                        _state.update { currentState ->
                            currentState.copy(
                                isAuthenticated = true
                            )
                        }
                    },
                    onFailure = { exception ->
                        when (exception) {
                            is AuthError -> {
                                _state.update { currentState ->
                                    currentState.copy(
                                        hasFeedback = true,
                                        feedbackUI = exception.toFeedbackUI()
                                    )
                                }
                            }

                            else -> {
                                _state.update { currentState ->
                                    currentState.copy(
                                        hasFeedback = true,
                                        feedbackUI = FeedbackType.ERROR to R.string.error_generic
                                    )
                                }
                            }
                        }
                    }
                )
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        hasFeedback = true,
                        feedbackUI = (e as? AuthError)?.toFeedbackUI()
                            ?: (FeedbackType.ERROR to R.string.error_generic)
                    )
                }
            }
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