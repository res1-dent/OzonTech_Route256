package com.ozontech.feature_products_impl.di

import androidx.lifecycle.ViewModel
import com.ozontech.core_utils.di.ViewModelKey
import com.ozontech.feature_products_impl.presentation.view_model.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

	@Binds
	@[IntoMap ViewModelKey(ProductListViewModel::class)]
	fun bindProductsViewModel(impl: ProductListViewModel): ViewModel
}