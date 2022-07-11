package com.ozontech.feature_products_impl.domain.view_objects

sealed class UiState {

	data class Success(
		val listOfProducts: List<ProductInListRecyclerViewModel>
	) : UiState()

	object Loading : UiState()
	object Error : UiState()
}

