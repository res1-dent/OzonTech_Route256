package com.ozontech.core_network_api.models

data class ProductInListDto(
	val guid: String,
	val image: List<String>,
	val name: String,
	val price: String,
	val rating: Double,
	val isFavorite: Boolean,
	val isInCart: Boolean?,
	val counter: Int
)

