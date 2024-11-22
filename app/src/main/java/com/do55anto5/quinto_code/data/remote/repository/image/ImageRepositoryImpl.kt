package com.do55anto5.quinto_code.data.remote.repository.image

import android.util.Log
import com.do55anto5.quinto_code.core.enums.image.ImageType
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getFirebaseStorage
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getUserId
import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository
import com.google.firebase.storage.StorageReference
import java.util.UUID
import kotlin.coroutines.suspendCoroutine

class ImageRepositoryImpl : ImageRepository {
    private val storage = getFirebaseStorage()

    private val baseImageRef = storage.getReference()
        .child("images")
        .child("users")
        .child(getUserId())

    private fun getStorageReference(imageType: ImageType): StorageReference {
        return when (imageType) {
            ImageType.PROFILE_PHOTO -> baseImageRef
                .child("profile-photo").child("profile-photo.jpg")

            ImageType.STORE_LOGO -> baseImageRef
                .child("store").child("logo").child("store-logo.jpg")

            ImageType.PRODUCT_IMAGE -> baseImageRef
                .child("store").child("products").child("${UUID.randomUUID()}.jpg")

            ImageType.SERVICE_IMAGE -> baseImageRef
                .child("store").child("services").child("${UUID.randomUUID()}.jpg")
        }
    }

    override suspend fun saveImage(imageBytes: ByteArray?, imageType: ImageType) {
        return suspendCoroutine { continuation ->
            imageBytes?.let { imageBytes ->
                val reference = getStorageReference(imageType)
                Log.d("ImageRepository", "Upload Start - Path: ${reference.path}")

                reference.putBytes(imageBytes)
                    .addOnSuccessListener { taskSnapshot ->
                        reference.downloadUrl
                            .addOnSuccessListener { uri ->
                                Log.d("ImageRepository", "Upload concluded, URL: $uri")
                                continuation.resumeWith(Result.success(Unit))
                            }
                            .addOnFailureListener { exception ->
                                Log.e(
                                    "ImageRepository",
                                    "Upload ok, but fail to generate URL",
                                    exception
                                )
                                continuation.resumeWith(Result.failure(exception))
                            }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("ImageRepository", "Upload failed", exception)
                        continuation.resumeWith(Result.failure(exception))
                    }
            }
        }
    }


    override suspend fun getImage(imageType: ImageType): String {
        return suspendCoroutine { continuation ->
            val reference = getStorageReference(imageType)
            Log.d("ImageRepository", "Try to get URL - Path: ${reference.path}")

            reference.downloadUrl
                .addOnSuccessListener { uri ->
                    Log.d("ImageRepository", "Got URL: $uri")
                    continuation.resumeWith(Result.success(uri.toString()))
                }
                .addOnFailureListener { exception ->
                    Log.e("ImageRepository", "Failed to get URL", exception)
                    Log.e("ImageRepository", "Exception: ", exception)
                    continuation.resumeWith(Result.failure(exception))
                }
        }
    }
}