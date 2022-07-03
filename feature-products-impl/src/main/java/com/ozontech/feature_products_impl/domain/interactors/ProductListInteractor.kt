package com.ozontech.feature_products_impl.domain.interactors

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import kotlinx.coroutines.flow.Flow

interface ProductListInteractor {

	suspend fun getProducts(): Flow<List<ProductInListDO>>

	suspend fun incrementCounter(guid: String)

	suspend fun updateInfo()
}