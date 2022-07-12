package com.ozontech.core_database_api.models

data class ProductDtoSharedPrefs(
	val guid: String,
	val name: String,
	val price: String,
	val description: String,
	val rating: Double,
	val isFavorite: Boolean,
	val isInCart: Boolean?,
	val images: List<String>,
	val weight: Double?,
	val count: Int?,
	val availableCount: Int,
	val additionalParams: Map<String, String>
)