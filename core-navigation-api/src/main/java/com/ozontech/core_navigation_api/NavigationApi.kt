package com.ozontech.core_navigation_api

import com.ozontech.feature_products_api.ProductNavigationApi

interface NavigationApi {
	fun getProductNavigation(): ProductNavigationApi
}
