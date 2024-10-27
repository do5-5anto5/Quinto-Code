package com.do55anto5.quinto_code.core.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.do55anto5.quinto_code.core.util.Permissions.Companion.CAMERAX_PERMISSIONS

fun hasCameraPermission(context: Context): Boolean {
    return CAMERAX_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

sealed class Permissions {
    companion object {
        val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}