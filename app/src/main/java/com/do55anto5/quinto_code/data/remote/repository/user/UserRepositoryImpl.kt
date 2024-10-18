package com.do55anto5.quinto_code.data.remote.repository.user

import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getDatabase
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {

    private val uid = FirebaseHelper.getUserId()

    override suspend fun saveUser(user: User) {
        uid.let {
            val userRef = getDatabase().collection("users").document(it)
            userRef.set(user)
        }
    }

    override suspend fun getUser(): User {
        uid.let {
            val docRef = getDatabase().collection("users").document(uid)
            val user = docRef.get()
                .await()
                .toObject(User::class.java)

            return user ?: User()
        }

    }
}