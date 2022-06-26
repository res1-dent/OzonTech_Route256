package com.ozontech.core_network_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.ProductsApi
import com.ozontech.core_network_impl.data.mappers.toProductDtoSharedPrefs
import com.ozontech.core_network_impl.data.mappers.toProductInListDtoSharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
	private val productsApi: ProductsApi,
	private val database: ProductsDatabase
) : WorkerRepository {

	override suspend fun getProductsInList() = withContext(Dispatchers.IO) {
		val productInList =
			productsApi.getProductsInList()
				.map { it.toProductInListDtoSharedPrefs() }
		database.addProductsInList(productInList)
	}

	override suspend fun getProducts() = withContext(Dispatchers.IO) {
		val products = productsApi.getProducts()
			.map { it.toProductDtoSharedPrefs() }
		database.addProducts(products)
	}
}

interface WorkerRepository {
	suspend fun getProductsInList()
	suspend fun getProducts()
}

