package com.ozontech.core_network_impl.data

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.core_network_impl.data.mappers.toProductInList
import com.ozontech.core_network_impl.mock.MockRepository
import java.util.*
import javax.inject.Inject

class ProductsApiImpl @Inject constructor(
    private val mockRepository: MockRepository
) : ProductsApi {

    override fun getProducts(): List<ProductInListDto> {
        return mockRepository.productInListDTOs
    }

    override fun incrementCounter(guid:String) {
        mockRepository.productInListDTOs.replaceAll {
            if (it.guid == guid)
                it.copy(counter = it.counter + 1)
            else it
        }
    }

    override fun getProductByGuid(guid: String): ProductDto? {
        return mockRepository.productDTO.find { guid == it.guid }
    }

    override fun addRandomProduct() {
        val newGuid = UUID.randomUUID().toString()
        val product = mockRepository.productDTO.random().copy(guid = newGuid)
        mockRepository.apply {
            productDTO.add(product)
            productInListDTOs.add(product.toProductInList())
        }

    }

}
