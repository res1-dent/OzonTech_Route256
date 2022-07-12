package com.ozontech.feature_products_impl.domain.repositories

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

	suspend fun fetchListOfProducts(): Flow<List<ProductInListDO>>

	suspend fun incrementCounter(guid: String)

	suspend fun updateInfo()

	suspend fun toggleCart(guid: String)

	suspend fun getProductsInCartCount(): Flow<Int>
}