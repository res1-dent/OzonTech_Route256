package com.ozontech.feature_add_product_impl.di

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.ProductsApi
import com.ozontech.core_network_api.WorkerApi

interface ProductAddFeatureDependencies {
    fun database(): ProductsDatabase
}
