package com.ozontech.feature_pdp_impl.domain.interactors

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import com.ozontech.feature_pdp_impl.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PdpInteractorImpl @Inject constructor(
	private val repository: ProductRepository
) : PdpInteractor {

	override suspend fun getProductByGuid(guid: String): Flow<ProductDO?> =
		repository.fetchProductDetails(guid)

	override suspend fun updateCart(guid: String, count: Int) {
		repository.updateCart(guid, count)
	}
}