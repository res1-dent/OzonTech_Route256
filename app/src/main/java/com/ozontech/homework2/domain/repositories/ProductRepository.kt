package com.ozontech.homework2.domain.repositories

import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import kotlinx.coroutines.flow.Flow

interface  ProductRepository {
     fun fetchListOfProducts(): List<ProductInListDO>
     fun incrementCounter(guid: String)
     fun fetchProductDetails(guid: String): ProductDO?
     fun addRandomProduct()
}