package com.ozontech.core_network_api

import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto

interface ProductsApi {
    fun getProducts(): List<ProductInListDto>
    fun incrementCounter(guid:String)
    fun getProductByGuid(guid: String): ProductDto?
    fun addRandomProduct()
}
