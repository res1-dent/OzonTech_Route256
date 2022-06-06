package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository

class
ProductListRepositoryImpl(
    private val mockRepository: MockRepository
) : ProductListRepository {


    override fun fetchListOfProducts(): List<ProductInListDO> =
        mockRepository.productInListDTOs.map { it.toDO() }

    override fun incrementCounter(guid: String) {
        mockRepository.productInListDTOs.replaceAll {
            if (it.guid == guid)
                it.copy(counter = it.counter + 1)
            else it
        }
    }
}