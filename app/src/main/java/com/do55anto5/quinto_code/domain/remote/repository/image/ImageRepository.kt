package com.do55anto5.quinto_code.domain.remote.repository.image

interface ImageRepository {
    suspend fun saveImage(imageBytes: ByteArray?)
}