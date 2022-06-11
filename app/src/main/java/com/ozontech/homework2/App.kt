package com.ozontech.homework2

import android.app.Application
import com.ozontech.core_navigation_impl.di.DIComponentStorage
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage
import kotlin.reflect.KClass

class App : Application(), DiStorage <DiComponent> {

	private val diStorage = DIComponentStorage()

	override fun initAndGet(className: KClass<DiComponent>): DiComponent {
		return diStorage.initAndGet(className)
	}

	override fun release(className: KClass<DiComponent>) {
		diStorage.release(className)
	}
}
