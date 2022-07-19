package com.ozontech.feature_pdp_impl.presentation.view_objects

sealed class PdpUiState {

	data class Success(val product: ProductVO, val amount: Int) : PdpUiState()

	object Error : PdpUiState()

	object Loading : PdpUiState()
}
