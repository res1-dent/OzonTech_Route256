package com.ozontech.core_network_api

import androidx.lifecycle.LiveData
import com.ozontech.core_network_api.models.ProductInListDto

interface GetProductsApi {
	fun getProducts(): LiveData<List<ProductInListDto>?>
}