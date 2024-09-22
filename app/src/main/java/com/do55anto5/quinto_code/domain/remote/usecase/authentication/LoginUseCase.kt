package com.do55anto5.quinto_code.domain.remote.usecase.authentication

import com.do55anto5.quinto_code.domain.remote.repository.authentication.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.login(email, password)
    }

}