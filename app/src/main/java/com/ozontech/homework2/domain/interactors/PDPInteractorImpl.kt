package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductRepository
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(
    private val repository: ProductRepository
): PDPInteractor {

    override fun getProductByGuid(guid: String): ProductVO {
        return repository.fetchProductDetails(guid).toVO()
    }

    override suspend fun getCounter(guid: String): Int {
       return repository.getCounter(guid)
    }

    override suspend fun incrementCounter(guid: String) {
        repository.incrementCounter(guid)
    }
}