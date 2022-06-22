package com.ozontech.feature_add_product_impl.di

import com.ozontech.core_database_api.ProductsDatabase

interface ProductAddFeatureDependencies {
	fun database(): ProductsDatabase
}
