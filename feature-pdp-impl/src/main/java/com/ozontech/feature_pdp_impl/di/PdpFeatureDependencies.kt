package com.ozontech.feature_pdp_impl.di

import com.ozontech.core_database_api.ProductsDatabase

interface PdpFeatureDependencies {

	fun database(): ProductsDatabase

}
