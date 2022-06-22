package com.ozontech.feature_products_impl.di

import com.ozontech.feature_products_impl.data.repositories.ProductRepositoryNetworkImpl
import com.ozontech.feature_products_impl.domain.repositories.ProductRepositoryNetwork
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

	@Binds
	fun bindProductsRepository(repository: ProductRepositoryNetworkImpl): ProductRepositoryNetwork


}

