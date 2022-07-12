package com.ozontech.feature_cart_impl.di

import androidx.lifecycle.ViewModel
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.MultiViewModelFactory
import com.ozontech.core_utils.di.PerFeature
import com.ozontech.core_utils.di.ViewModelKey
import com.ozontech.feature_cart_impl.data.repositories.CartRepositoryImpl
import com.ozontech.feature_cart_impl.domain.interactors.CartInteractor
import com.ozontech.feature_cart_impl.domain.interactors.CartInteractorImpl
import com.ozontech.feature_cart_impl.domain.repositories.CartRepository
import com.ozontech.feature_cart_impl.presentation.view.CartViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@PerFeature
@Component(
	modules = [FeatureCartModule::class],
	dependencies = [FeatureCartComponent.CartFeatureDependenciesComponent::class]
)
interface FeatureCartComponent : DiComponent {

	fun getFabric(): MultiViewModelFactory

	@Component(dependencies = [DatabaseApi::class])
	interface CartFeatureDependenciesComponent {
		fun provideDatabase(): ProductsDatabase
	}
}

@Module
interface FeatureCartModule {

	@PerFeature
	@Binds
	fun bindsInteractor(impl: CartInteractorImpl): CartInteractor

	@PerFeature
	@Binds
	fun bindsRepository(impl: CartRepositoryImpl): CartRepository

	@PerFeature
	@Binds
	@[IntoMap ViewModelKey(CartViewModel::class)]
	fun bindProductsViewModel(impl: CartViewModel): ViewModel
}