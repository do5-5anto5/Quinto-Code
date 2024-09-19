package com.do55anto5.quinto_code.domain.remote.usecase.user

import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository

class SaveUserUseCase (
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }

}