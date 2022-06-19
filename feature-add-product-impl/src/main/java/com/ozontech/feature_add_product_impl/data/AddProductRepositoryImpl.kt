package com.ozontech.feature_add_product_impl.data

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
	private val databaseApi: ProductsDatabase
): AddProductRepository {

	override fun addRandomProduct() {
		databaseApi.addRandomProduct()
	}
}