package com.ozontech.feature_pdp_impl.presentation.view_objects

data class ProductVO(
	val guid: String,
	val name: String,
	val price: Int,
	val description: String,
	val rating: Float,
	val isFavorite: Boolean,
	val isInCart: Boolean?,
	val images: List<String>,
	val weight: Double,
	val count: Int,
	val availableCount: Int,
	val additionalParams: Map<String, String>,
)