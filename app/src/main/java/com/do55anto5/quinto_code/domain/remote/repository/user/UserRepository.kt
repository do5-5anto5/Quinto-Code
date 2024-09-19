package com.do55anto5.quinto_code.domain.remote.repository.user

import com.do55anto5.quinto_code.domain.remote.model.User

interface UserRepository {

    suspend fun saveUser(user: User)

}