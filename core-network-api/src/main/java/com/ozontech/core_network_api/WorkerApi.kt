package com.ozontech.core_network_api

import androidx.lifecycle.LiveData
import com.ozontech.core_network_api.models.ProductInListDto

interface WorkerApi {
	fun getProducts(): LiveData<List<ProductInListDto>?>
}