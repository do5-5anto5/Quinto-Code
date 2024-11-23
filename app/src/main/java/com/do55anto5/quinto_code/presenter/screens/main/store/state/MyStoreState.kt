package com.do55anto5.quinto_code.presenter.screens.main.store.state

import android.graphics.Bitmap
import android.net.Uri
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType


data class MyStoreState(
    val isLoading: Boolean = true,
    val vendor: String = "",
    val name: String = "",
    val description: String = "",
    val city: String = "",
    val address: String = "",
    val phoneNumber:String = "",
    val logo: String? = "",
    val hasFeedBack: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val currentImageUri: Uri = Uri.EMPTY,
    val compressedImage: Pair<Bitmap?, ByteArray?>? = null
)