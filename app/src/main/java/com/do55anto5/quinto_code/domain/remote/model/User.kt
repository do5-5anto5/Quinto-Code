package com.do55anto5.quinto_code.domain.remote.model

import com.google.firebase.Timestamp

data class User(
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val photo: String? = null,
    val city: String? = null,
    val storeRef: String? = null,
    val isVendor: Boolean = false,
    val createdAt: Timestamp? = null
)