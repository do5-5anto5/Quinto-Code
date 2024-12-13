package com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel


import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.image.ImageType.PROFILE_PHOTO
import com.do55anto5.quinto_code.core.enums.input.EditFieldType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getCurrentUserEmail
import com.do55anto5.quinto_code.core.util.reduceImageSize
import com.do55anto5.quinto_code.core.util.toDrawable
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.usecase.image.GetProfilePhotoUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.image.SaveProfilePhotoUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.GetUserUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.SaveUserUseCase
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import com.google.firebase.Timestamp
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveProfilePhotoUseCase: SaveProfilePhotoUseCase,
    private val getProfilePhotoUseCase: GetProfilePhotoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadUser()
    }

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

            is ProfileAction.ResetErrorState -> {
                resetErrorState()
            }

            is ProfileAction.OnImagePick -> {
                onImagePick(
                    action.context,
                    action.currentImage
                )
            }
        }
    }

    private fun onImagePick(context: Context, currentImage: Uri?) {
        try {
            viewModelScope.launch {
                startLoading()
                _state.update { currentState ->
                    currentState.copy(
                        currentImageUri = currentImage ?: Uri.EMPTY
                    )
                }
                _state.value.currentImageUri.toDrawable(context)?.let { drawable ->
                    val compressedImage = context.reduceImageSize(drawable)
                    _state.update { currentState ->
                        currentState.copy(
                            compressedImage = compressedImage
                        )
                    }
                }
                stopLoading()
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
            stopLoading()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val userDeferred = async { getUserUseCase() }
                val photoDeferred = async {
                    try {
                        getProfilePhotoUseCase(PROFILE_PHOTO)
                    } catch (e: Exception) {
                        Log.e("ProfileViewModel", "Error loading photo", e)
                        ""
                    }
                }

                val user = userDeferred.await()
                val photo = photoDeferred.await()

                _state.update { currentState ->
                    currentState.copy(
                        name = user.name ?: "",
                        surname = user.surname ?: "",
                        city = user.city ?: "",
                        email = getCurrentUserEmail(),
                        photo = photo,
                        createdAt = user.createdAt
                    )
                }
                Log.i("ProfileViewModel", "User loaded: $user")
                stopLoading()
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
                stopLoading()
            }
        }
    }

    private fun onSave() {
        startLoading()
        viewModelScope.launch {
            try {
                delay(1200)
                saveUserUseCase(
                    user = User(
                        name = _state.value.name,
                        surname = _state.value.surname,
                        city = _state.value.city,
                        email = _state.value.email,
                        createdAt = if (_state.value.createdAt == null) Timestamp.now() else _state.value.createdAt
                    )
                )
                _state.value.compressedImage?.second?.let {
                    saveProfilePhotoUseCase(it, imageType = PROFILE_PHOTO)
                }

                stopLoading()
                if (!_state.value.isLoading) {
                    _state.update { currentState ->
                        currentState.copy(
                            hasFeedBack = true,
                            feedbackUI = Pair(
                                FeedbackType.SUCCESS,
                                R.string.snack_bar_text_success_profile_screen
                            )
                        )
                    }
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
                stopLoading()
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

    private fun resetErrorState() {
        _state.update { currentState ->
            currentState.copy(
                hasFeedBack = false,
                feedbackUI = null
            )
        }
    }

    private fun startLoading() {
        _state.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
    }

    private fun stopLoading() {
        _state.update { currentState ->
            currentState.copy(
                isLoading = false
            )
        }
    }

}