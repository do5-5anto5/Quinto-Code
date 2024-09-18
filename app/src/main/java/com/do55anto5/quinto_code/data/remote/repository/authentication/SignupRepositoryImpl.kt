package com.do55anto5.quinto_code.data.remote.repository.authentication

import com.do55anto5.quinto_code.domain.remote.repository.authentication.SignupRepository
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.suspendCoroutine

class SignupRepositoryImpl(
    private val auth: FirebaseAuth
) : SignupRepository {

    override suspend fun register(email: String, password: String) {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        continuation.resumeWith(Result.success(Unit))
                    } else {
//                        continuation.resumeWith(Result.failure(task.exception ?: Exception("Unknown error")))
                    }
                }
        }
    }

}