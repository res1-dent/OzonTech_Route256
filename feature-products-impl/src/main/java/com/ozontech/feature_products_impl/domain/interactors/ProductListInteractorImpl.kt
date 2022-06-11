package com.ozontech.feature_products_impl.domain.interactors

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
    private val repository: com.ozontech.feature_products_impl.domain.repositories.ProductRepository
) : ProductListInteractor {

    override fun getProducts(): List<ProductInListDO> =
        repository.fetchListOfProducts()

    override fun incrementCounter(guid: String) {
       repository.incrementCounter(guid)
    }
}