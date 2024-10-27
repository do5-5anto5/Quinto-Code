package com.do55anto5.quinto_code.presenter.screens.camera.state

import android.graphics.Bitmap
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType


data class CameraState(
    val isLoading: Boolean = false,
    val isPhotoTaken: Boolean = false,
    val bitmap: Bitmap? = null,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null
)