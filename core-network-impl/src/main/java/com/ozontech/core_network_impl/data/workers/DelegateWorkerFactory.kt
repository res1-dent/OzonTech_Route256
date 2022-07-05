package com.ozontech.core_network_impl.data.workers

import androidx.work.DelegatingWorkerFactory
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DelegateWorkerFactory @Inject constructor(
	workerRepository: WorkerRepository
) : DelegatingWorkerFactory() {
	init {
		addFactory(ProductsWorkerFactory(workerRepository))
	}
}