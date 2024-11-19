package com.do55anto5.quinto_code.domain.remote.repository.image

interface ImageRepository {

    suspend fun saveProfilePhoto(imageBytes: ByteArray?)

    suspend fun getProfilePhoto() : String
}