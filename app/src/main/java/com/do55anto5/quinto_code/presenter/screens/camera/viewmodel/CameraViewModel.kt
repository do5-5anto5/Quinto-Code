package com.do55anto5.quinto_code.presenter.screens.camera.viewmodel


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.presenter.screens.camera.action.CameraAction
import com.do55anto5.quinto_code.presenter.screens.camera.state.CameraState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CameraViewModel : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun submitAction(action: CameraAction) {
        when (action) {
            is CameraAction.OnTakePhoto -> {
                takePhoto(
                    action.context,
                    action.controller,
                    action.onPhotoTaken
                )
            }
            is CameraAction.OnCancel -> {
                onCancel()
            }
        }
    }

    fun onPhotoTaken(bitmap: Bitmap) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    bitmap = bitmap,
                    isPhotoTaken = true
                )
            }
        }
    }

    private fun takePhoto(
        context: Context,
        controller: LifecycleCameraController,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }
            controller.takePicture(
                ContextCompat.getMainExecutor(context),
                object : OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        super.onCaptureSuccess(image)

                        val matrix = Matrix().apply {
                            postRotate(image.imageInfo.rotationDegrees.toFloat())
                        }
                        val rotatedBitmap = Bitmap.createBitmap(
                            image.toBitmap(),
                            0,
                            0,
                            image.width,
                            image.height,
                            matrix,
                            true
                        )

                        onPhotoTaken(rotatedBitmap)

                        _state.update { currentState ->
                            currentState.copy(
                                isLoading = false
                            )
                        }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        super.onError(exception)
                        _state.update { currentState ->
                            currentState.copy(
                                hasFeedback = true,
                                feedbackUI = Pair(
                                    FeedbackType.ERROR,
                                    FirebaseHelper.validateError(exception.message)
                                ),
                                isLoading = false
                            )
                        }
                    }
                }
            )
        }
    }

    private fun onCancel() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isPhotoTaken = false
                )
            }
        }
    }
}