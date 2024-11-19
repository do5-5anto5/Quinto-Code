package com.do55anto5.quinto_code.data.remote.repository.image

import android.util.Log
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getFirebaseStorage
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getUserId
import com.do55anto5.quinto_code.domain.remote.repository.image.ImageRepository
import kotlin.coroutines.suspendCoroutine

class ImageRepositoryImpl : ImageRepository {
    private val storage = getFirebaseStorage()

    private val profilePhotoRef = storage.getReference()
        .child("images")
        .child("users")
        .child(getUserId())
        .child("profile-photo")
        .child("profile-photo.jpg")

    override suspend fun saveProfilePhoto(imageBytes: ByteArray?) {
        return suspendCoroutine { continuation ->
            imageBytes?.let { imageBytes ->
                Log.d("ImageRepository", "Upload Start - Path: ${profilePhotoRef.path}")

                profilePhotoRef.putBytes(imageBytes)
                    .addOnSuccessListener { taskSnapshot ->
                        profilePhotoRef.downloadUrl
                            .addOnSuccessListener { uri ->
                                Log.d("ImageRepository", "Upload concluded, URL: $uri")
                                continuation.resumeWith(Result.success(Unit))
                            }
                            .addOnFailureListener { exception ->
                                Log.e("ImageRepository", "Upload ok, but fail to generate URL", exception)
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

    override suspend fun getProfilePhoto(): String {
        return suspendCoroutine { continuation ->
            Log.d("ImageRepository", "Try to get URL - Path: ${profilePhotoRef.path}")

            profilePhotoRef.downloadUrl
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