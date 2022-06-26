package com.ozontech.core_network_api

import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto
import retrofit2.http.GET

interface ProductsApi {

	@GET("50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
	suspend fun getProductsInList(): List<ProductInListDto>

	@GET("8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
	suspend fun getProducts(): List<ProductDto>

}
