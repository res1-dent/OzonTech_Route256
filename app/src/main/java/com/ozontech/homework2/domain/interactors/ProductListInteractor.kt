package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.presentation.viewObjects.ProductInListVO

interface ProductListInteractor {
    fun getProducts(): List<ProductInListVO>
}