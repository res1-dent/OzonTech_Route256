package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.repositories.ProductRepository

class ProductRepositoryImpl(
    private val mock: MockRepository
) : ProductRepository {

    override  fun fetchProductDetails(guid: String): ProductDO {
        return mock.productDTO.find { it.guid == guid }?.toDO() ?: error("No such element")
    }
}