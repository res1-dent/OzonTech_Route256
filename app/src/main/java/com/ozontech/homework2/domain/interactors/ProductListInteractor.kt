package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import kotlinx.coroutines.flow.Flow

interface ProductListInteractor {
    suspend fun getProducts(): Flow<List<ProductInListVO>>
    suspend fun incrementCounter(guid: String)
}