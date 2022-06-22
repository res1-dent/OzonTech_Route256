package com.ozontech.homework2

import android.app.Application
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage
import com.ozontech.homework2.di.DIComponentStorageImpl
import kotlin.reflect.KClass


class App : Application(), DiStorage<DiComponent> {


	override fun onCreate() {
		super.onCreate()

	}

	private val diStorage: DIComponentStorageImpl by lazy {
		DIComponentStorageImpl(this)
	}

	override fun initAndGet(className: KClass<DiComponent>): DiComponent {
		return diStorage.initAndGet(className)
	}

	override fun release(className: KClass<DiComponent>) {
		diStorage.release(className)
	}

}
