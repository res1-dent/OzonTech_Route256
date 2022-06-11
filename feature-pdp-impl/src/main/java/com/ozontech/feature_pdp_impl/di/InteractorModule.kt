package com.ozontech.feature_pdp_impl.di

import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractor
import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

	@Binds
	fun bindProductsInteractor(impl: PdpInteractorImpl): PdpInteractor
}