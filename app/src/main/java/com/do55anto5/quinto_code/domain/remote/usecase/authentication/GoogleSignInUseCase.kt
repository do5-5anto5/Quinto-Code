package com.do55anto5.quinto_code.domain.remote.usecase.authentication

import android.content.Context
import com.do55anto5.quinto_code.domain.remote.repository.authentication.AuthenticationRepository

class GoogleSignInUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(context: Context): Result<Unit> {
        return repository.signInWithGoogle(context)
    }
}