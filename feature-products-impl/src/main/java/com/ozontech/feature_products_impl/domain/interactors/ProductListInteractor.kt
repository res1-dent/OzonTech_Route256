package com.ozontech.feature_products_impl.domain.interactors

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.view_objects.UiState
import kotlinx.coroutines.flow.Flow

interface ProductListInteractor {

	suspend fun getProducts(): Flow<UiState>

	suspend fun incrementCounter(guid: String)

	suspend fun updateInfo()

	suspend fun toggleCart(guid: String, isInCart: Boolean)
}