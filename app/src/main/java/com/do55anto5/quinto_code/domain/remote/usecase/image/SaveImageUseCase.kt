package com.do55anto5.quinto_code.domain.remote.usecase.image

import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository

class SaveImageUseCase(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(
        imageBytes: ByteArray?) {
        repository.saveImage(imageBytes)
    }
}