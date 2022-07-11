package com.ozontech.feature_pdp_impl.domain.repositories

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
	suspend fun fetchProductDetails(guid: String): Flow<ProductDO?>
	suspend fun updateCart(guid: String, count: Int)
}