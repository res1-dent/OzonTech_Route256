package com.ozontech.core_navigation_impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozontech.core_navigation_impl.di.CoreNavigationComponent
import com.ozontech.core_navigation_impl.di.DaggerCoreNavigationComponent
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.DiStorage

class MainActivity : AppCompatActivity(R.layout.activity_main) {


	@Suppress("UNCHECKED_CAST")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as DiStorage<DiComponent>).putComponent(getNavigationComponent())
	}


	private fun getNavigationComponent(): CoreNavigationComponent {
		return DaggerCoreNavigationComponent.builder().fragmentManager(supportFragmentManager)
			.build()
	}

}