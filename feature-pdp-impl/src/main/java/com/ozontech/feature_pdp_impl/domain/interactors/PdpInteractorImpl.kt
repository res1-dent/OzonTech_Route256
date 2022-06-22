package com.ozontech.feature_pdp_impl.domain.interactors

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import com.ozontech.feature_pdp_impl.domain.repositories.ProductRepository
import javax.inject.Inject

class PdpInteractorImpl @Inject constructor(
	private val repository: ProductRepository
) : PdpInteractor {

	override fun getProductByGuid(guid: String): ProductDO? =
		repository.fetchProductDetails(guid)
}