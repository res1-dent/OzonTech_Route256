package com.ozontech.core_network_impl.di

import android.content.Context
import androidx.work.*
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.core_network_impl.data.repositories.WorkerRepository
import com.ozontech.core_network_impl.data.workers.DelegateWorkerFactory
import com.ozontech.core_network_impl.data.workers.ProductInListWorker
import com.ozontech.core_network_impl.data.workers.ProductsWorker
import com.ozontech.core_utils.di.DiComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(
	modules = [RetrofitModule::class, WorkerModule::class, TestModule::class],
	dependencies = [CoreNetworkDependencies::class]
)
abstract class CoreNetworkComponent : NetworkApi, DiComponent {

	abstract fun getRepository(): WorkerRepository
	abstract fun getWorkManager(): WorkerManager
	abstract fun getWorkerConfiguration(): Configuration

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun context(context: Context): Builder
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

@Module
class TestModule {
	@Singleton
	@Provides
	fun provideWorkManagerConfiguration(
		delegateWorkerFactory: DelegateWorkerFactory
	): Configuration {
		return Configuration.Builder()
			.setMinimumLoggingLevel(android.util.Log.DEBUG)
			.setWorkerFactory(delegateWorkerFactory)
			.build()
	}
	@Singleton
	@Provides
	fun providesWorkManager(context: Context): WorkManager{
		return WorkManager.getInstance(context)
	}
}
