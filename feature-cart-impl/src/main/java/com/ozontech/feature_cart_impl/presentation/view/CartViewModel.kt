package com.ozontech.feature_cart_impl.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.feature_cart_impl.domain.interactors.CartInteractor
import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartViewModel @Inject constructor(
	private val interactor: CartInteractor
) : ViewModel() {

	private val listOfProductsMutableStateFlow = MutableStateFlow<List<Product>>(emptyList())
	val listOfProductsStateFlow = listOfProductsMutableStateFlow.asStateFlow()

	val totalPriceSharedFlow = interactor.footerSharedFlow

	init {
		viewModelScope.launch {
			interactor.getListOfProductsInCart().collect {
				listOfProductsMutableStateFlow.emit(it)
			}
		}
	}

	suspend fun updateCart(guid: String, count: Int) = withContext(Dispatchers.IO) {
		if (this.isActive.not()) return@withContext
		interactor.updateCart(guid, count)
	}


}