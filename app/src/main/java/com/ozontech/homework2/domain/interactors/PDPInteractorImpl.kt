package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.repositories.ProductRepository

class PDPInteractorImpl (
    private val repository: ProductRepository
) : PDPInteractor {

    override  fun getProductByGuid(guid: String): ProductDO? =
        repository.fetchProductDetails(guid)
}