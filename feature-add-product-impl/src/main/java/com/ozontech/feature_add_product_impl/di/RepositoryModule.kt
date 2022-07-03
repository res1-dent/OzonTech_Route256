package com.ozontech.feature_add_product_impl.di

import com.ozontech.feature_add_product_impl.data.AddProductRepositoryImpl
import com.ozontech.feature_add_product_impl.domain.repository.AddProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

	@Binds
	fun bindProductsRepository(repository: AddProductRepositoryImpl): AddProductRepository
}
