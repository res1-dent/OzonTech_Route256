package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
    private val repository: ProductListRepository
) : ProductListInteractor {
    override fun getProducts(): List<ProductInListVO> =
        repository.fetchListOfProducts().map { it.toVO() }

}