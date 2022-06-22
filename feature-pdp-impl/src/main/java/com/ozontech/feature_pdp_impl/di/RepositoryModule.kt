package com.ozontech.feature_pdp_impl.di

import com.ozontech.feature_pdp_impl.data.repositories.ProductRepositoryImpl
import com.ozontech.feature_pdp_impl.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
	@Binds
	fun bindProductsRepository(repository: ProductRepositoryImpl): ProductRepository
}