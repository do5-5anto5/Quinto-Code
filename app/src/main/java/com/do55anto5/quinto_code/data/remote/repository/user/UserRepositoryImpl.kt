package com.do55anto5.quinto_code.data.remote.repository.user

import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl : UserRepository {

    private val usersReference =
        FirebaseHelper.getDatabase()
            .child("users")
            .child(FirebaseHelper.getUserId())

    override suspend fun saveUser(user: User) {
        return suspendCoroutine { continuation ->
            usersReference
                .setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                    }
                }
        }
    }

}