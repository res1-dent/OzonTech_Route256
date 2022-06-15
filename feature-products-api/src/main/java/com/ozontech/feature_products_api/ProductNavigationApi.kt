package com.ozontech.feature_products_api

import androidx.fragment.app.Fragment

interface ProductNavigationApi {
    fun navigateToPDP(guid: String)
    fun navigateToAdd()
}
