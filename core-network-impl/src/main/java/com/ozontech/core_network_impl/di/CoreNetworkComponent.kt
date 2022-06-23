package com.ozontech.core_network_impl.di

import androidx.work.WorkManager
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import com.ozontech.core_utils.di.DiComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [RetrofitModule::class, WorkerModule::class],
	dependencies = [CoreNetworkDependencies::class]
)
abstract class CoreNetworkComponent : NetworkApi, DiComponent {

	abstract fun getRepository(): WorkerRepository
	abstract fun getWorkManager(): WorkerManager

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun workManager(workManager: WorkManager): Builder
		fun dependencies(deps: CoreNetworkDependencies): Builder
		fun build(): CoreNetworkComponent
	}

	@Singleton
	@Component(dependencies = [DatabaseApi::class])
	interface CoreNetworkDependenciesComponent : CoreNetworkDependencies
}

interface CoreNetworkDependencies {
	fun getDatabase(): ProductsDatabase
}

