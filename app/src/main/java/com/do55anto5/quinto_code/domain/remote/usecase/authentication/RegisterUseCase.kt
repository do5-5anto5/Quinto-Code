package com.do55anto5.quinto_code.domain.remote.usecase.authentication

import com.do55anto5.quinto_code.domain.remote.repository.authentication.SignupRepository

class RegisterUseCase(
    private val repository: SignupRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.register(email, password)
    }

}