package com.do55anto5.quinto_code.domain.remote.repository.authentication

import android.content.Context

interface AuthenticationRepository {

    suspend fun register(email: String, password: String)

    suspend fun login(email: String, password: String)

    suspend fun signInWithGoogle(context: Context): Result<Unit>

    suspend fun forgotPassword(email: String)

}