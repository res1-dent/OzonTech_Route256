package com.ozontech.feature_products_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.feature_products_impl.data.mappers.toProductInListDO
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject
constructor(
	private val database: ProductsDatabase,
	private val workerManager: WorkerManager
) : ProductRepository {

	override suspend fun fetchListOfProducts(): Flow<List<ProductInListDO>> =
		database.productsInList.map {
			it.map { it.toProductInListDO() }
		}

	override suspend fun getProductsInCartCount(): Flow<Int> =
		database.productsInCart.map { it.size }

	override suspend fun incrementCounter(guid: String) {
		database.incrementCounter(guid)
	}

	override suspend fun updateInfo() {
		workerManager.startWorkers()
	}

	override suspend fun toggleCart(guid: String) {
		database.toggleCart(guid)
	}

}