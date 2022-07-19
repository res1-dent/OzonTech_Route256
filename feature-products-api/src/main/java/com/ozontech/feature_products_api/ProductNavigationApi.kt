package com.ozontech.feature_products_api

interface ProductNavigationApi {
	fun navigateToPDP(guid: String)
	fun navigateToAdd()
	fun navigateToCart()
}
