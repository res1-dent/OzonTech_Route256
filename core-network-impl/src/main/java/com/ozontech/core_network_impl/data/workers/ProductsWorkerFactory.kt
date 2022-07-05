package com.ozontech.core_network_impl.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsWorkerFactory @Inject constructor(
	private val workerRepository: WorkerRepository
) : WorkerFactory() {

	override fun createWorker(
		appContext: Context,
		workerClassName: String,
		workerParameters: WorkerParameters
	): ListenableWorker? {
		// This only handles a single Worker, please don’t do this!!
		// See below for a better way using DelegatingWorkerFactory
		return when (workerClassName) {
			ProductInListWorker::class.java.name -> ProductInListWorker(
				appContext,
				workerParameters,
				workerRepository
			)
			ProductsWorker::class.java.name -> ProductsWorker(
				appContext,
				workerParameters,
				workerRepository
			)
			else -> null
		}
	}
}