package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ProductListInteractorImpl(
    private val repository: ProductListRepository
) : ProductListInteractor {
    override  fun getProducts(): Flow<List<ProductInListVO>> = flow {
        repository.fetchListOfProducts().collect {
            emit(it.map { productInListDO -> productInListDO.toVO() })
        }
    }

    override  fun incrementCounter(guid: String) {
        repository.incrementCounter(guid)
    }
}