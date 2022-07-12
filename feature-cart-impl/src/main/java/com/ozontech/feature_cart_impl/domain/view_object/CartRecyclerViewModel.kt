package com.ozontech.feature_cart_impl.domain.view_object




data class Product(
	val id: String,
	val name: String,
	val imageUlr: String,
	val price: Int,
	val amount: Int
)