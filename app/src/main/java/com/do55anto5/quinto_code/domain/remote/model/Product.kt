package com.do55anto5.quinto_code.domain.remote.model

import com.do55anto5.quinto_code.core.enums.product.ProductType

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val photo: String,
    val type: ProductType
)