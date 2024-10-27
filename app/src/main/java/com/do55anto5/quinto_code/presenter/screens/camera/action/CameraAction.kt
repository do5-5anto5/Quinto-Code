package com.do55anto5.quinto_code.presenter.screens.camera.action

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController


sealed class CameraAction {
    data class OnTakePhoto(
        val context: Context,
        val controller: LifecycleCameraController,
        val onPhotoTaken: (Bitmap) -> Unit
    ) : CameraAction()
    data object OnCancel : CameraAction()
}