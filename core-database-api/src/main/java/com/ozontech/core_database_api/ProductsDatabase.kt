package com.ozontech.core_database_api

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs

interface ProductsDatabase {
	fun addProductsInList(list: List<ProductInListDtoSharedPrefs>)
	fun getProductsInList(): List<ProductInListDtoSharedPrefs>
	fun addProducts(list: List<ProductDtoSharedPrefs>)
	fun getProducts(): List<ProductDtoSharedPrefs>
	fun getProductByGuid(guid:String): ProductDtoSharedPrefs?
	fun incrementCounter(guid: String)
}