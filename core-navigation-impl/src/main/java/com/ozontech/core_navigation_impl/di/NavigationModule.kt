package com.ozontech.core_navigation_impl.di

import com.ozontech.core_navigation_impl.navigation.ProductNavigationImpl
import com.ozontech.feature_products_api.ProductNavigationApi
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi
}
