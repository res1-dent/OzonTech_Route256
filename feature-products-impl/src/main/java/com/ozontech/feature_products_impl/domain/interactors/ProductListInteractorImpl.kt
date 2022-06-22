package com.ozontech.feature_products_impl.domain.interactors

import androidx.lifecycle.LiveData
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepositoryNetwork
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
	private val repository: ProductRepositoryNetwork
) : ProductListInteractor {

	override fun getProducts(): LiveData<List<ProductInListDO>?> =
		repository.fetchListOfProducts()

	override fun incrementCounter(guid: String) {
		repository.incrementCounter(guid)
	}

}