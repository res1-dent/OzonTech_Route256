package com.ozontech.feature_add_product_impl.di

import com.ozontech.feature_add_product_impl.domain.interactor.AddInteractor
import com.ozontech.feature_add_product_impl.domain.interactor.AddInteractorImpl
import dagger.Binds
import dagger.Module


@Module
interface InteractorModule {

	@Binds
	fun bindProductsInteractor(impl: AddInteractorImpl): AddInteractor
}