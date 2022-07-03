package com.ozontech.feature_pdp_impl.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractor
import com.ozontech.feature_pdp_impl.presentation.mappers.toVO
import com.ozontech.feature_pdp_impl.presentation.view_objects.PdpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PdpViewModel @Inject constructor(
	private val interactor: PdpInteractor,
) : ViewModel() {

	private val _productMutableStateFlow = MutableStateFlow<PdpUiState>(PdpUiState.Loading)
	val productStateFlow = _productMutableStateFlow.asStateFlow()

	fun getProductByGuid(guid: String) {
		viewModelScope.launch {
			interactor.getProductByGuid(guid).collect {
				it?.let {
					_productMutableStateFlow.emit(PdpUiState.Success(it.toVO()))
				} ?: _productMutableStateFlow.emit(PdpUiState.Error)
			}
		}
	}

	fun updateCart(guid: String, count: Int) {
		viewModelScope.launch {
			interactor.updateCart(guid, count)
		}
	}

}

