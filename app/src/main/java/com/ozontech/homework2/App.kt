package com.ozontech.homework2

import android.app.Application
import android.util.Log
import androidx.work.WorkManager
import com.ozontech.homework2.di.DIComponentStorageImpl
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage
import kotlin.reflect.KClass


class App : Application(),  DiStorage<DiComponent>{


	override fun onCreate() {
		Log.e("Worker", "OncreateApp")
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

	override fun putComponent(component: DiComponent) {
		diStorage.putComponent(component)
	}
}
