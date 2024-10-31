package com.do55anto5.quinto_code.presenter.screens.main.profile.state

import android.graphics.Bitmap
import android.net.Uri
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType


data class ProfileState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val city: String = "",
    val hasFeedBack: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val isLoading: Boolean = true,
    val isUserLoaded: Boolean = false,
    val currentImageUri: Uri = Uri.EMPTY,
    val compressedImage: Pair<Bitmap?, ByteArray?>? = null
)