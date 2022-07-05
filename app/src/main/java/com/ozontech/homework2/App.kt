package com.ozontech.homework2

import android.app.Application
import androidx.work.Configuration
import com.ozontech.core_network_impl.di.CoreNetworkComponent
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage
import com.ozontech.core_utils.getComponent
import com.ozontech.homework2.di.DIComponentStorageImpl
import javax.inject.Inject
import kotlin.reflect.KClass


class App : Application(), DiStorage<DiComponent>, Configuration.Provider {

	private val diStorage: DIComponentStorageImpl by lazy {
		DIComponentStorageImpl(this)
	}

	private val networkComponent by lazy {
		getComponent(CoreNetworkComponent::class)
	}

	override fun onCreate() {
		super.onCreate()
		networkComponent.getWorkManager().startWorkers()
	}

	override fun initAndGet(className: KClass<DiComponent>): DiComponent {
		return diStorage.initAndGet(className)
	}

	override fun release(className: KClass<DiComponent>) {
		diStorage.release(className)
	}

	override fun getWorkManagerConfiguration(): Configuration {
		return networkComponent.getWorkerConfiguration()
	}

}
