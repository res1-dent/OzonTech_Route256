package com.ozontech.feature_pdp_impl.domain.interactors

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO

interface PdpInteractor {

	fun getProductByGuid(guid: String): ProductDO?
}