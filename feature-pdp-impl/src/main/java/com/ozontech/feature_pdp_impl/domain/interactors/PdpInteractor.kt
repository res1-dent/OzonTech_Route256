package com.ozontech.feature_pdp_impl.domain.interactors

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import kotlinx.coroutines.flow.Flow

interface PdpInteractor {

	suspend fun getProductByGuid(guid: String): Flow<ProductDO?>

	suspend fun updateCart(guid: String, count: Int)

	suspend fun getAmountIfIsInCart(guid: String): Flow<Int>
}