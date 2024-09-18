package com.do55anto5.quinto_code.domain.remote.repository.authentication

interface SignupRepository {

    suspend fun register(email: String, password: String)

}