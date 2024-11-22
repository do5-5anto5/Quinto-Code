package com.do55anto5.quinto_code.domain.remote.usecase.image

import com.do55anto5.quinto_code.core.enums.image.ImageType
import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository

class SaveProfilePhotoUseCase(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(
        imageBytes: ByteArray?, imageType: ImageType) {
        repository.saveImage(imageBytes, imageType)
    }
}