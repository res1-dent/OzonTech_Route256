package com.ozontech.core_network_impl.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozontech.core_network_api.GetProductsApi
import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.core_network_impl.domain.key.Key
import javax.inject.Inject

class GetProductsApiImpl @Inject constructor(
	private val workManager: WorkManager
): GetProductsApi {

	override fun getProducts(): LiveData<List<ProductInListDto>?> {
		return Transformations.map(workManager.getWorkInfosByTagLiveData(Key.TAG_PRODUCT_IN_LIST_REQUEST)){
			if (it != null && it.isNotEmpty() && it.first().state.isFinished) {
				val currentWorkInfo = it.first()
				currentWorkInfo.outputData.getString(Key.KEY_OUTPUT_PRODUCTS_IN_LIST_WORKER)?.let { json ->
					val listType = object : TypeToken<List<ProductInListDto>>() {}.type
					Gson().fromJson<List<ProductInListDto>>(json, listType)
				}
			} else null
		}
	}
}