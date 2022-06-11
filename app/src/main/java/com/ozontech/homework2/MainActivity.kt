package com.ozontech.homework2

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ozontech.feature_add_product_impl.presentation.view.ProductAddFragment
import com.ozontech.feature_pdp_impl.presentation.view.PdpFragment
import com.ozontech.feature_products_api.ProductNavigationApi

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	companion object : ProductNavigationApi {
		override fun navigateToPDP(fragment: Fragment, guid: String) {
			fragment.parentFragmentManager.beginTransaction().replace(
				R.id.fragmentContainerView,
				PdpFragment.newInstance(guid)
			).addToBackStack(null).commit()
		}

		override fun navigateToAdd(fragment: Fragment) {
			fragment.parentFragmentManager.beginTransaction().replace(
				R.id.fragmentContainerView,
				ProductAddFragment()
			).addToBackStack(null).commit()
		}
	}
}