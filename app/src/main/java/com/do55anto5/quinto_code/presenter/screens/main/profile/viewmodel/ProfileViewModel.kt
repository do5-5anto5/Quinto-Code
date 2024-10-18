package com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.input.EditFieldType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.usecase.user.GetUserUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.SaveUserUseCase
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun submitAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.OnValueChange -> {
                onValueChange(
                    type = action.type,
                    value = action.value
                )
            }

            is ProfileAction.OnSave -> {
                onSave()
            }

            is ProfileAction.OnGetUser -> {
                loadUser()
            }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val user = getUserUseCase()

                _state.update { currentState ->
                    currentState.copy(
                        name = user.name ?: "",
                        surname = user.surname ?: "",
                        city = user.city ?: "",
                        email = user.email ?: "",
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
                        )
                    )
                }
            }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            try {
                saveUserUseCase(
                    user = User(
                        name = _state.value.name,
                        surname = _state.value.surname,
                        city = _state.value.city
                    )
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

    private fun onValueChange(type: EditFieldType, value: String) {
        when (type) {
            EditFieldType.NAME -> {
                changeName(value)
            }

            EditFieldType.SURNAME -> {
                changeSurname(value)
            }

            EditFieldType.CITY -> {
                changeCity(value)
            }
        }
    }

    private fun changeName(value: String) {
        _state.update { currentState ->
            currentState.copy(
                name = value
            )
        }
    }

    private fun changeSurname(value: String) {
        _state.update { currentState ->
            currentState.copy(
                surname = value
            )
        }
    }

    private fun changeCity(value: String) {
        _state.update { currentState ->
            currentState.copy(
                city = value
            )
        }
    }

}