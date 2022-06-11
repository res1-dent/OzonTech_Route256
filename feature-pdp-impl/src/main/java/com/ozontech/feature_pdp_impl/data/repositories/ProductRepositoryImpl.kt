package com.ozontech.feature_pdp_impl.data.repositories

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.feature_pdp_impl.data.mappers.toDO
import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import com.ozontech.feature_pdp_impl.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
	private val productsApi: ProductsApi
): ProductRepository {
	override fun fetchProductDetails(guid: String): ProductDO? {
		return productsApi.getProductByGuid(guid)?.toDO()
	}
}