package com.do55anto5.quinto_code.domain.remote.model

import com.google.firebase.Timestamp

data class Store(
    val vendorRef: String,
    val logoRef: String? = null,
    val name: String,
    val address: String? = null,
    val phoneNumber: String? = null,
    val createdAt: Timestamp = Timestamp.now()
)