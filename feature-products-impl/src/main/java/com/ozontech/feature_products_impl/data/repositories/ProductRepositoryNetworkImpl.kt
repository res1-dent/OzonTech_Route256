package com.ozontech.feature_products_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.feature_products_impl.data.mappers.toProductInListDO
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepositoryNetwork
import javax.inject.Inject

class ProductRepositoryNetworkImpl @Inject
constructor(
	private val workerManager: WorkerManager,
	private val database: ProductsDatabase
) : ProductRepositoryNetwork {

	override fun fetchListOfProducts(): List<ProductInListDO> {
		workerManager.startWorkers()
		return database.getProductsInList().map { it.toProductInListDO() }
	}

	override fun incrementCounter(guid: String) {
		database.incrementCounter(guid)
	}
}