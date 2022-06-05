package com.ozontech.homework2.di

import com.ozontech.homework2.data.repositories.AddRepositoryImpl
import com.ozontech.homework2.data.repositories.ProductListRepositoryImpl
import com.ozontech.homework2.data.repositories.ProductRepositoryImpl
import com.ozontech.homework2.domain.repositories.AddRepository
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun providesProductListRepository(impl: ProductListRepositoryImpl): ProductListRepository

    @Binds
    @ViewModelScoped
    fun providesProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @ViewModelScoped
    fun providesAddRepository(impl: AddRepositoryImpl): AddRepository

}