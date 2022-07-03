package com.ozontech.core_network_api

import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto
import retrofit2.http.GET

interface ProductsApi {

	@GET("ee6876a1-8c02-45aa-bde4-b91817a8b210")
	suspend fun getProductsInList(): List<ProductInListDto>

	@GET("d1b4763b-a5ea-471f-83bf-796da466e3d8")
	suspend fun getProducts(): List<ProductDto>

}
