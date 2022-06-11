package com.ozontech.feature_add_product_impl.data

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
	private val productsApi: ProductsApi
): AddProductRepository {

	override fun addRandomProduct() {
			productsApi.addRandomProduct()
	}
}