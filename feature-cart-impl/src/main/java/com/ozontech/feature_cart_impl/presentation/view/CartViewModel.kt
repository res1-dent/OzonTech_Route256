package com.ozontech.feature_cart_impl.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.feature_cart_impl.domain.interactors.CartInteractor
import com.ozontech.feature_cart_impl.presentation.view_objects.CartState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartViewModel @Inject constructor(
	private val interactor: CartInteractor
) : ViewModel() {

	private val cartStateMutableSharedFlow = MutableStateFlow<CartState>(CartState.Init)
	val cartStateSharedFlow = cartStateMutableSharedFlow.asStateFlow()

	private val totalPriceSharedFlow = interactor.footerSharedFlow

	init {
		viewModelScope.launch {
			interactor.getListOfProductsInCart()
				.zip(totalPriceSharedFlow) { products, totalPrice ->
					if (products.isEmpty())
						CartState.Empty
					else CartState.Success(products, totalPrice)
				}.collectLatest {
					cartStateMutableSharedFlow.emit(it)
				}
		}
	}

	suspend fun updateCart(guid: String, count: Int) = withContext(Dispatchers.IO) {
		if (this.isActive.not()) return@withContext
		interactor.updateCart(guid, count)
	}


}