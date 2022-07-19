package com.ozontech.feature_cart_impl.domain.repositories

import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface CartRepository {
	val footerStateFlow : MutableSharedFlow<Int>
	suspend fun getProductsInCart(): Flow<List<Product>>
	suspend fun updateCart(guid: String, count: Int)
}