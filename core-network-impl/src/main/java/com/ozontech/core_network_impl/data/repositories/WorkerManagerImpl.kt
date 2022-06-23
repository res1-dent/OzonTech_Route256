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
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.core_network_impl.data.workers.ProductInListWorker
import com.ozontech.core_network_impl.data.workers.ProductsWorker
import com.ozontech.core_network_impl.domain.key.Key
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerManagerImpl @Inject constructor(
	private val workManager: WorkManager,
) : WorkerManager {

	override fun startWorkers() {
		val getProductIntListRequest = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
			.setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
			.addTag(Key.TAG_PRODUCT_IN_LIST_REQUEST)
			.build()
		val getProductsRequest = OneTimeWorkRequest.Builder(ProductsWorker::class.java).build()
		workManager.beginUniqueWork(
			PRODUCT_IN_LIST_WORK,
			ExistingWorkPolicy.KEEP,
			getProductIntListRequest
		).then(getProductsRequest).enqueue()
	}

	companion object {
		private const val PRODUCT_IN_LIST_WORK = "product_in_list_work"
	}

}