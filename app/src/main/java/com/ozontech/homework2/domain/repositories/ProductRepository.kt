package com.ozontech.homework2.domain.repositories

import com.ozontech.homework2.domain.domainObjects.ProductDO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProductDetails(guid: String): ProductDO
}