package com.ozontech.feature_add_product_impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.feature_add_product_impl.domain.interactor.AddInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
	private val interactor: AddInteractor
) : ViewModel() {

	fun addRandomProduct() {
		viewModelScope.launch {
			interactor.addRandomProduct()
		}
	}
}
