package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import kotlinx.coroutines.flow.Flow

interface ProductListInteractor {
     fun getProducts(): List<ProductInListVO>
     fun incrementCounter(guid: String)
}