package com.do55anto5.quinto_code.core.custom_errors

import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

sealed class AuthError : Exception() {
    data class CredentialError(val type: String, override val message: String?) : AuthError()

    data object UserCancelled : AuthError() {
        private fun readResolve(): Any = UserCancelled
    }

    data object InvalidCredential : AuthError() {
        private fun readResolve(): Any = InvalidCredential
    }

    data object NetworkError : AuthError() {
        private fun readResolve(): Any = NetworkError
    }

    data class Unknown(val exception: Exception) : AuthError()

    companion object {
        fun fromException(e: Exception): AuthError {
            return when (e) {
                is GetCredentialCancellationException -> UserCancelled
                is GetCredentialException -> CredentialError(e.type, e.errorMessage?.toString())
                is FirebaseAuthInvalidCredentialsException -> InvalidCredential
                is FirebaseNetworkException -> NetworkError
                else -> Unknown(e)
            }
        }
    }

    fun toFeedbackUI(): Pair<FeedbackType, Int> {
        return when (this) {
            is CredentialError -> when (type) {
                // Adicione mais casos conforme necessário
                else -> FeedbackType.ERROR to R.string.error_generic
            }
            is UserCancelled -> FeedbackType.ERROR to R.string.error_sign_in_cancelled
            is InvalidCredential -> FeedbackType.ERROR to R.string.error_invalid_credential
            is NetworkError -> FeedbackType.ERROR to R.string.error_network
            is Unknown -> FeedbackType.ERROR to R.string.error_generic
        }
    }
}