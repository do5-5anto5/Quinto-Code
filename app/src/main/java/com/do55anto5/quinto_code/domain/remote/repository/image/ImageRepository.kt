package com.do55anto5.quinto_code.domain.remote.repository.image

import com.do55anto5.quinto_code.core.enums.image.ImageType

interface ImageRepository {

    suspend fun saveImage(imageBytes: ByteArray?, imageType: ImageType)

    suspend fun getImage(imageType: ImageType) : String
}