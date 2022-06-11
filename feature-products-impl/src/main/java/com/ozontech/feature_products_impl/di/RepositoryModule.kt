package com.ozontech.feature_products_impl.di

import com.ozontech.feature_products_impl.data.repositories.ProductRepositoryImpl
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductsRepository(repository: ProductRepositoryImpl): ProductRepository
}
