package com.do55anto5.quinto_code.data.remote.repository.image

import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getFirebaseStorage
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getUserId
import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository
import kotlin.coroutines.suspendCoroutine

class ImageRepositoryImpl : ImageRepository {
    private val storageRef = getFirebaseStorage().reference
    private val imageRef = storageRef
        .child(
            "images/${System.currentTimeMillis()}${getUserId()}.jpg"
        )

    override suspend fun saveImage(imageBytes: ByteArray?) {
        return suspendCoroutine {
            imageBytes?.let { imageBytes ->
                imageRef.putBytes(imageBytes)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            it.resumeWith(Result.success(Unit))
                        } else {
                            it.resumeWith(Result.failure(task.exception!!))
                        }
                    }
            }
        }
    }
}