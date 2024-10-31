package com.do55anto5.quinto_code.data.remote.repository.image

import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getFirebaseStorage
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getUserId
import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository

class ImageRepositoryImpl : ImageRepository {

    override suspend fun saveImage(imageBytes: ByteArray?) {
        val storageRef = getFirebaseStorage().reference
        val imageRef = storageRef
            .child(
                "images/${System.currentTimeMillis()}${getUserId()}.jpg"
            )

        imageRef.let { imageRef.putBytes(imageBytes!!) }
    }
}