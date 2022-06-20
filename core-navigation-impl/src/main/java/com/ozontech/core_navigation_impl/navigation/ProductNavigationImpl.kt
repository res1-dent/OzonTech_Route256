package com.ozontech.core_navigation_impl.navigation

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ozontech.core_navigation_impl.MainActivity
import com.ozontech.core_navigation_impl.R
import com.ozontech.core_navigation_impl.di.CoreNavigationComponent
import com.ozontech.core_utils.getComponent
import com.ozontech.feature_add_product_impl.presentation.view.ProductAddFragment
import com.ozontech.feature_pdp_impl.presentation.view.PdpFragment
import com.ozontech.feature_products_api.ProductNavigationApi
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {

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
