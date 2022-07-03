package com.ozontech.feature_products_impl.di

import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractorImpl
import dagger.Binds
import dagger.Module


@Module
interface InteractorModule {

	@Binds
	fun bindProductsInteractor(impl: ProductListInteractorImpl): ProductListInteractor
}