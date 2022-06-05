package com.ozontech.homework2.di

import com.ozontech.homework2.domain.interactors.PDPInteractor
import com.ozontech.homework2.domain.interactors.PDPInteractorImpl
import com.ozontech.homework2.domain.interactors.ProductListInteractor
import com.ozontech.homework2.domain.interactors.ProductListInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface InteractorsModule {

    @Binds
    @ViewModelScoped
    fun providesProductListInteractor(impl: ProductListInteractorImpl): ProductListInteractor

    @Binds
    @ViewModelScoped
    fun providesProductInteractor(impl: PDPInteractorImpl): PDPInteractor
}