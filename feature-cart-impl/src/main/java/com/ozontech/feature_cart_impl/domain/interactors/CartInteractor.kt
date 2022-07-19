package com.ozontech.feature_cart_impl.domain.interactors

import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface CartInteractor {
	val footerSharedFlow : SharedFlow<Int>
	suspend fun getListOfProductsInCart(): Flow<List<Product>>
	suspend fun updateCart(guid: String, count: Int)
}