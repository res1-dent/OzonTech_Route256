package com.ozontech.homework2.data.dto


data class ProductInList(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val counter: Int = 0
)

