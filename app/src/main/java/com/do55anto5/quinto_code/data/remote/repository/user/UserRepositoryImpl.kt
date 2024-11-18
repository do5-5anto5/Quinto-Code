package com.do55anto5.quinto_code.data.remote.repository.user

import com.do55anto5.quinto_code.core.helper.FirebaseHelper
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.getDatabase
import com.do55anto5.quinto_code.domain.remote.model.User
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl : UserRepository {


    override suspend fun saveUser(user: User) {
        val uid = FirebaseHelper.getUserId()
        val userRef = getDatabase().collection("users").document(uid)
        return suspendCoroutine {
            userRef.set(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        it.resumeWith(Result.success(Unit))
                    } else {
                        it.resumeWith(Result.failure(task.exception!!))
                    }
                }
        }
    }

    override suspend fun getUser(): User {
        val uid = FirebaseHelper.getUserId()
        val userRef = getDatabase().collection("users").document(uid)
        return suspendCoroutine {
            userRef.get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        it.resumeWith(Result.success(task.result.toObject(User::class.java) ?: User()))
                    } else {
                        it.resumeWith(Result.failure(task.exception!!))
                    }
                }
        }

    }
}