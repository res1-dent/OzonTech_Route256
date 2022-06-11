package com.ozontech.core_network_impl.di

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.core_network_impl.data.ProductsApiImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindProductApi(api: ProductsApiImpl): ProductsApi
}
