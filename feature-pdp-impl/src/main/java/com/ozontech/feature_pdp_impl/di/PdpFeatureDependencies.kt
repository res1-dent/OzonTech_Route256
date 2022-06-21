package com.ozontech.feature_pdp_impl.di

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_network_api.ProductsApi

interface PdpFeatureDependencies {

    fun database(): ProductsDatabase

}
