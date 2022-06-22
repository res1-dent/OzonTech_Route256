package com.ozontech.core_network_impl.data.workers

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import com.ozontech.core_network_impl.di.CoreNetworkComponent
import com.ozontech.core_network_impl.domain.key.Key
import com.ozontech.core_utils.getComponent

class ProductInListWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

	private val repository: WorkerRepository =
		applicationContext.getComponent(CoreNetworkComponent::class).getRepository()

	override fun doWork(): Result {
		return try {
			repository.getProductsInList()?.let {
				Result.success(
					Data.Builder().putString(
						Key.KEY_OUTPUT_PRODUCTS_IN_LIST_WORKER,
						Gson().toJson(repository.getProductsInList())
					).build()
				)
			} ?: Result.failure()
		} catch (e: Exception) {
			Result.failure()
		}
	}
}