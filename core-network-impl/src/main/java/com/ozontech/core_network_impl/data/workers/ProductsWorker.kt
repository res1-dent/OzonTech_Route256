package com.ozontech.core_network_impl.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import com.ozontech.core_network_impl.di.CoreNetworkComponent
import com.ozontech.core_utils.getComponent

class ProductsWorker(context: Context, params: WorkerParameters) :
	CoroutineWorker(context, params) {

	private val repository: WorkerRepository =
		applicationContext.getComponent(CoreNetworkComponent::class).getRepository()

	override suspend fun doWork(): Result {
		return try {
			repository.getProducts()
			Result.success()
		} catch (e: Exception) {
			Result.failure()
		}
	}
}