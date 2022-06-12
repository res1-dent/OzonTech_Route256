package com.ozontech.core_navigation_impl

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ozontech.feature_products_api.ProductNavigationApi

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	companion object : ProductNavigationApi {
		override fun navigateToPDP(fragment: Fragment, guid: String) {

		}

		override fun navigateToAdd(fragment: Fragment) {

		}
	}
}