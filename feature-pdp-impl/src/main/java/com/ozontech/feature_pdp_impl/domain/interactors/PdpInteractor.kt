package com.ozontech.feature_pdp_impl.domain.interactors

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import kotlinx.coroutines.flow.Flow

interface PdpInteractor {

	suspend fun getProductByGuid(guid: String): Flow<ProductDO?>
}