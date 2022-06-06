package com.ozontech.homework2.domain.repositories

import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import kotlinx.coroutines.flow.Flow

interface  ProductListRepository {
     fun fetchListOfProducts(): List<ProductInListDO>
     fun incrementCounter(guid: String)
}