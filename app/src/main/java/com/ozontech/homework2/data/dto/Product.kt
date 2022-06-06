package com.ozontech.homework2.data.dto


data class Product(
    val guid: String,
    val name: String,
    val price: String,
    val description: String,
    val rating: Double = 0.0,
    val isFavorite: Boolean = false,
    val isInCart: Boolean = false,
    val images: List<String>,
    val weight: Double? = null,
    val count: Int? = null,
    val availableCount: Int? = null,
    val additionalParams: Map<String, String> = emptyMap()
)