package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.data.mappers.toProductInList
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductRepository
import java.util.*

class ProductRepositoryImpl(
    private val mockRepository: MockRepository
) : ProductRepository {


    override fun fetchListOfProducts(): List<ProductInListDO> =
        mockRepository.productInListDTOs.map { it.toDO() }

    override fun incrementCounter(guid: String) {
        mockRepository.productInListDTOs.replaceAll {
            if (it.guid == guid)
                it.copy(counter = it.counter + 1)
            else it
        }
    }

    override fun addRandomProduct() {
        val newGuid = UUID.randomUUID().toString()
        val product = mockRepository.productDTO.random().copy(guid = newGuid)
        mockRepository.apply {
            productDTO.add(product)
            productInListDTOs.add(product.toProductInList())
        }
    }

    override  fun fetchProductDetails(guid: String): ProductDO? {
        return mockRepository.productDTO.find { it.guid == guid }?.toDO()
    }
}