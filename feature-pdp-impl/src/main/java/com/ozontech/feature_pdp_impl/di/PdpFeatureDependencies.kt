package com.ozontech.feature_pdp_impl.di

import com.ozontech.core_network_api.ProductsApi

interface PdpFeatureDependencies {

    fun productsApi(): ProductsApi
}
