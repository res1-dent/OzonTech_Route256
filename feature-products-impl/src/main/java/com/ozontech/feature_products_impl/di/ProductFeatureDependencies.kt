package com.ozontech.feature_products_impl.di

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_network_api.WorkerManager
import com.ozontech.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {

	fun navigation(): ProductNavigationApi

	fun database(): ProductsDatabase

	fun getWorkerManager(): WorkerManager

}
