package com.ozontech.core_database_api

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInCartSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
import kotlinx.coroutines.flow.Flow

interface ProductsDatabase {
	val products: Flow<List<ProductDtoSharedPrefs>>
	val productsInList: Flow<List<ProductInListDtoSharedPrefs>>
	val productsInCart: Flow<List<ProductInCartSharedPrefs>>
	suspend fun addProductsInList(list: List<ProductInListDtoSharedPrefs>)
	suspend fun addProducts(list: List<ProductDtoSharedPrefs>)
	suspend fun incrementCounter(guid: String)
	suspend fun addRandomProduct()
	suspend fun toggleCart(guid: String)
	suspend fun addProductToCart(guid: String, count: Int)
}
