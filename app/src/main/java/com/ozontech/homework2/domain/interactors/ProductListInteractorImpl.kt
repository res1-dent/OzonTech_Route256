package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
    private val repository: ProductListRepository
) : ProductListInteractor {
    override suspend fun getProducts(): Flow<List<ProductInListVO>> = flow {
        repository.fetchListOfProducts().collect {
            emit(it.map { productInListDO -> productInListDO.toVO() })
        }
    }
}