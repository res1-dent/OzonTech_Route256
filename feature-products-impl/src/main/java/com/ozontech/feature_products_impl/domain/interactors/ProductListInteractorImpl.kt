package com.ozontech.feature_products_impl.domain.interactors

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
	private val repository: ProductRepository
) : ProductListInteractor {

	override suspend fun getProducts(): Flow<List<ProductInListDO>> =
		repository.fetchListOfProducts()

	override suspend fun incrementCounter(guid: String) {
		repository.incrementCounter(guid)
	}

	override suspend fun updateInfo() {
		repository.updateInfo()
	}

}