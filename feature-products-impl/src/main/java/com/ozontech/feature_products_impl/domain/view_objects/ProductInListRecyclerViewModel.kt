package com.ozontech.feature_products_impl.domain.view_objects

sealed class ProductInListRecyclerViewModel {

	data class ProductInListVO(
		val guid: String,
		val image: List<String>,
		val name: String,
		val price: Int,
		val rating: Float,
		val counter: String,
		val isInCart: Boolean
	) : ProductInListRecyclerViewModel()

	data class Header(val text: String): ProductInListRecyclerViewModel()
}
