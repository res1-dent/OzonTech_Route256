package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository

class ProductListInteractorImpl(
    private val repository: ProductListRepository
) : ProductListInteractor {

    override fun getProducts(): List<ProductInListDO> =
        repository.fetchListOfProducts()

    override fun incrementCounter(guid: String) {
        repository.incrementCounter(guid)
    }
}