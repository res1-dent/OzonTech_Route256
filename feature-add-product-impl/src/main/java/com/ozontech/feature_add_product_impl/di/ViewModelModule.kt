package com.ozontech.feature_add_product_impl.di

import androidx.lifecycle.ViewModel
import com.ozontech.core_utils.di.ViewModelKey
import com.ozontech.feature_add_product_impl.presentation.viewModel.AddViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

	@Binds
	@[IntoMap ViewModelKey(AddViewModel::class)]
	fun bindProductsViewModel(impl: AddViewModel): ViewModel
}