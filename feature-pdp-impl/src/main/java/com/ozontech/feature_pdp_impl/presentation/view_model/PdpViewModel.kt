package com.ozontech.feature_pdp_impl.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractor
import com.ozontech.feature_pdp_impl.presentation.mappers.toVO
import com.ozontech.feature_pdp_impl.presentation.view_objects.PdpUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PdpViewModel @Inject constructor(
	private val interactor: PdpInteractor,
) : ViewModel() {

	private val _productMutableStateFlow = MutableStateFlow<PdpUiState>(PdpUiState.Loading)
	val productStateFlow = _productMutableStateFlow.asStateFlow()

	fun getProductByGuid(guid: String) {
		viewModelScope.launch {
			interactor.getProductByGuid(guid)
				.combine(interactor.getAmountIfIsInCart(guid)) { products, amount ->
					products?.let {
						PdpUiState.Success(products.toVO(), amount)
					} ?: PdpUiState.Error
				}.collect {
				_productMutableStateFlow.emit(it)
			}
		}
	}

	suspend fun updateCart(guid: String, count: Int) = withContext(Dispatchers.IO) {
		if (this.isActive.not()) return@withContext
		interactor.updateCart(guid, count)
	}

}

