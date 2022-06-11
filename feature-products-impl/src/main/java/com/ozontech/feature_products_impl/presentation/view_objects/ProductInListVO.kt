package com.ozontech.feature_products_impl.presentation.view_objects

data class ProductInListVO(
    val guid: String,
    val image: String,
    val name: String,
    val price: Int,
    val rating: Float,
    val counter: String
)