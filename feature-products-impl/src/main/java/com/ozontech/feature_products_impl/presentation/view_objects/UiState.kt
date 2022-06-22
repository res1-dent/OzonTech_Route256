package com.ozontech.feature_products_impl.presentation.view_objects

sealed class UiState {

	data class Success(
		val listOfProducts: List<ProductInListVO>
	) : UiState()
	object Loading : UiState()
	object Error : UiState()
}

