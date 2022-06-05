package com.ozontech.homework2.presentation.viewObjects

data class ProductVO(
    val guid: String,
    val name: String,
    val price: String,
    val description: String,
    val rating: Float,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val weight: Double?,
    val count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>
)