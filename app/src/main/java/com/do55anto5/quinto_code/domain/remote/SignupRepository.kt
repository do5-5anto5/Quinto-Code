package com.do55anto5.quinto_code.domain.remote

interface SignupRepository {

    suspend fun register(email: String, password: String)

}