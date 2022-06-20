package com.ozontech.feature_pdp_impl.di

import androidx.lifecycle.ViewModel
import com.ozontech.core_utils.di.ViewModelKey
import com.ozontech.feature_pdp_impl.presentation.view_model.PdpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

	@Binds
	@[IntoMap ViewModelKey(PdpViewModel::class)]
	fun bindProductsViewModel(impl: PdpViewModel): ViewModel
}