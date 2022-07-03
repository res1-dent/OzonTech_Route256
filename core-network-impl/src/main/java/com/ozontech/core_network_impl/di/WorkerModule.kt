package com.ozontech.core_network_impl.di

import com.ozontech.core_network_api.WorkerManager
import com.ozontech.core_network_impl.data.repositories.WorkerManagerImpl
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import com.ozontech.core_network_impl.data.repositories.WorkerRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface WorkerModule {
	@Binds
	@Singleton
	fun bindNetworkRepository(repository: WorkerManagerImpl): WorkerManager

	@Binds
	@Singleton
	fun bindWorkerRepository(repository: WorkerRepositoryImpl): WorkerRepository
}
