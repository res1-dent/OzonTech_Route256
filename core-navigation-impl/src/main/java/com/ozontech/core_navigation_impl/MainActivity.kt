package com.ozontech.core_navigation_impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ozontech.core_navigation_impl.di.DIComponentStorage
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage
import com.ozontech.feature_products_api.ProductNavigationApi
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(R.layout.activity_main), DiStorage<DiComponent> {


	private val diStorage = DIComponentStorage(supportFragmentManager)


	override fun initAndGet(className: KClass<DiComponent>): DiComponent {
		return diStorage.initAndGet(className)
	}

	override fun release(className: KClass<DiComponent>) {
		diStorage.release(className)
	}
}