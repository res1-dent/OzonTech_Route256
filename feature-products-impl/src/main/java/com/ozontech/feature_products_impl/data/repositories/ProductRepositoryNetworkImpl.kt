package com.ozontech.feature_products_impl.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.GetProductsApi
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.feature_products_impl.data.mappers.toDO
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepositoryNetwork
import javax.inject.Inject

class ProductRepositoryNetworkImpl @Inject
constructor(
	private val getProductsApi: GetProductsApi,
	private val workerManager: WorkerManager,
	private val database: ProductsDatabase
) : ProductRepositoryNetwork {

	override fun fetchListOfProducts(): LiveData<List<ProductInListDO>?> {
		workerManager.startWorkers()
		return Transformations.map(getProductsApi.getProducts()) { remoteList ->
			remoteList?.let {
				it.map { Dto -> Dto.toDO() }
			}
		}
	}

	override fun incrementCounter(guid: String) {
		database.incrementCounter(guid)
	}
}