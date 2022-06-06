package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.mappers.toProductInList
import com.ozontech.homework2.domain.repositories.AddRepository
import java.util.*

class AddRepositoryImpl(
    private val mockRepository: MockRepository
) : AddRepository {

    override fun addRandomProduct() {
        val newGuid = UUID.randomUUID().toString()
        val product = mockRepository.productDTO.random().copy(guid = newGuid)
        mockRepository.apply {
            productDTO.add(product)
            productInListDTOs.add(product.toProductInList())
        }
    }


}