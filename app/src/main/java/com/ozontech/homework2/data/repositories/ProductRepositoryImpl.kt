package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.repositories.ProductRepository

class ProductRepositoryImpl(
    private val mockRepository: MockRepository
) : ProductRepository {

    override  fun fetchProductDetails(guid: String): ProductDO {
        return mockRepository.productDTO.find { it.guid == guid }?.toDO() ?: error("No such element")
    }
}