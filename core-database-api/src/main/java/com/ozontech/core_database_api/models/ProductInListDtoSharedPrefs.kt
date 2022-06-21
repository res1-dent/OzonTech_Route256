package com.ozontech.core_database_api.models

data class ProductInListDtoSharedPrefs(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val counter: Int
)

