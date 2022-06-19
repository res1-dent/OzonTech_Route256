package com.ozontech.core_navigation_impl.navigation

import androidx.fragment.app.FragmentManager
import com.ozontech.core_navigation_impl.R
import com.ozontech.feature_pdp_impl.presentation.view.PdpFragment
import com.ozontech.feature_products_api.ProductNavigationApi
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor(
    private val fragmentManager: FragmentManager
) : ProductNavigationApi {

    override fun navigateToPDP(guid: String) {
        fragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView,
            PdpFragment.newInstance(guid)
        ).addToBackStack(null).commit()
    }

    override fun navigateToAdd() {
       /* fragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView,
            ProductAddFragment()
        ).addToBackStack(null).commit()*/
    }


}
