package com.do55anto5.quinto_code.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

suspend fun Uri.toDrawable(context: Context): Drawable? = withContext(Dispatchers.IO) {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(this@toDrawable)
    return@withContext Drawable.createFromStream(inputStream, this@toDrawable.toString())
}

suspend fun Context.reduceImageSize(drawable: Drawable?): Pair<Bitmap?, ByteArray?> =
    withContext(Dispatchers.IO) {
        if (drawable == null) return@withContext Pair(null, null)

        val originWidth = drawable.intrinsicWidth
        val originHeight = drawable.intrinsicHeight
        val newWidth = (originWidth * 0.5).toInt()
        val newHeight = (originHeight * 0.5).toInt()

        val baos = ByteArrayOutputStream()
        val bitmap = drawable.toBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos)

        val imageBytes = baos.toByteArray()
        return@withContext Pair(
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size),
            imageBytes
        )
    }