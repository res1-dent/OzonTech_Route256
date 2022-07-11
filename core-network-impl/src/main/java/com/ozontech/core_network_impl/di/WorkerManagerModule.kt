package com.ozontech.core_network_impl.di

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import com.ozontech.core_network_impl.data.workers.DelegateWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorkerManagerModule {
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
	fun providesWorkManager(context: Context): WorkManager {
		return WorkManager.getInstance(context)
	}
}