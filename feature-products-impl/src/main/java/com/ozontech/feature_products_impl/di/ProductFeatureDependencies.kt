package com.ozontech.feature_products_impl.di

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {

    fun productsApi(): ProductsApi

    fun navigation(): ProductNavigationApi
}
