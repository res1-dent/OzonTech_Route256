package com.ozontech.core_network_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.ProductsApi
import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.core_network_impl.data.mappers.toProductDtoSharedPrefs
import com.ozontech.core_network_impl.data.mappers.toProductInListDto
import com.ozontech.core_network_impl.data.mappers.toProductInListDtoSharedPrefs
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
	private val productsApi: ProductsApi,
	private val database: ProductsDatabase
) : WorkerRepository {

	override fun getProductsInList(): List<ProductInListDto>? {
		return productsApi.getProductsInList().execute().body()?.also {
			database.addProductsInList(it.map { it.toProductInListDtoSharedPrefs() })
		}?.let { database.getProductsInList().map { it.toProductInListDto() } }
	}

	override fun getProducts(): List<ProductDto>? {
		return productsApi.getProducts().execute().body()?.also {
			database.addProducts(it.map { it.toProductDtoSharedPrefs() })
		}
	}
}

interface WorkerRepository {
	fun getProductsInList(): List<ProductInListDto>?
	fun getProducts(): List<ProductDto>?
}
