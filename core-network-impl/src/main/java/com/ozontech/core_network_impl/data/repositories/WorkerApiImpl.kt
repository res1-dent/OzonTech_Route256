package com.ozontech.core_network_impl.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozontech.core_network_api.WorkerApi
import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.core_network_impl.data.workers.ProductInListWorker
import com.ozontech.core_network_impl.data.workers.ProductsWorker
import com.ozontech.core_network_impl.domain.key.Key
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerApiImpl @Inject constructor(
	private val workManager: WorkManager,
) : WorkerApi {

	override fun getProducts(): LiveData<List<ProductInListDto>?> {
		val request = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
			.setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
			.build()
		val request2 = OneTimeWorkRequest.Builder(ProductsWorker::class.java).build()
		workManager.beginUniqueWork(PRODUCT_IN_LIST_WORK, ExistingWorkPolicy.KEEP, request)
			.then(request2).enqueue()
		return Transformations.map(workManager.getWorkInfoByIdLiveData(request.id)) {
			if (it != null && it.state.isFinished) {
				it.outputData.getString(Key.KEY_OUTPUT_PRODUCTS_IN_LIST_WORKER)?.let { json ->
					val listType = object : TypeToken<List<ProductInListDto>>() {}.type
					Gson().fromJson<List<ProductInListDto>>(json, listType)
				}
			} else null
		}
	}

	companion object {
		private const val PRODUCT_IN_LIST_WORK = "product_in_list_work"
	}

}