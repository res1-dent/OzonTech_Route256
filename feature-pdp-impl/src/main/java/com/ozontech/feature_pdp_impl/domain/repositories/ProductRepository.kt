package com.ozontech.feature_pdp_impl.domain.repositories

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO

interface ProductRepository {

	fun fetchProductDetails(guid: String): ProductDO?
}