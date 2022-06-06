package com.ozontech.homework2.domain.repositories

import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import kotlinx.coroutines.flow.Flow

interface  ProductListRepository {
    suspend fun fetchListOfProducts(): Flow<List<ProductInListDO>>
    suspend fun incrementCounter(guid: String)
}