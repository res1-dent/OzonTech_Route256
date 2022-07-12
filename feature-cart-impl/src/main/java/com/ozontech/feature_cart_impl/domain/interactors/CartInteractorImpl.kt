package com.ozontech.feature_cart_impl.domain.interactors

import com.ozontech.feature_cart_impl.domain.repositories.CartRepository
import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class CartInteractorImpl @Inject constructor(
	private val repository: CartRepository
): CartInteractor {

	override val footerSharedFlow: SharedFlow<Int> = repository.footerStateFlow.asSharedFlow()

	override suspend fun getListOfProductsInCart(): Flow<List<Product>> {
		return repository.getProductsInCart()
	}

	override suspend fun updateCart(guid: String, count: Int) {
		repository.updateCart(guid, count)
	}
}