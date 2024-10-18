package com.do55anto5.quinto_code.data.remote.repository.user

import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getDatabase
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository

class UserRepositoryImpl : UserRepository {

    override suspend fun saveUser(user: User) {

        FirebaseHelper.getUserId().let {
            val uid = it
            val userRef = getDatabase().collection("users").document(uid)
            userRef.set(user)
        }
    }

}