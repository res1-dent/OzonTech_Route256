package com.ozontech.feature_add_product_impl.di

import com.ozontech.core_network_api.ProductsApi

interface ProductAddFeatureDependencies {
    fun productsApi(): ProductsApi
}
