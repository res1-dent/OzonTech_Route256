package com.ozontech.feature_pdp_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.feature_pdp_impl.data.mappers.toDO
import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import com.ozontech.feature_pdp_impl.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
	private val database: ProductsDatabase
) : ProductRepository {
	override suspend fun fetchProductDetails(guid: String): Flow<ProductDO?> =
		database.products.map { it.firstOrNull { it.guid == guid } }.map { it?.toDO() }

	override suspend fun updateCart(guid: String, count: Int) {
		database.addProductToCart(guid, count)
	}
}