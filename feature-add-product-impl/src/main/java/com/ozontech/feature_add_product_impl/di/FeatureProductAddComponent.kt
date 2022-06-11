package com.ozontech.feature_add_product_impl.di

import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.MultiViewModelFactory
import com.ozontech.core_utils.di.PerFeature
import dagger.Component

@PerFeature
@Component(
	modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
	dependencies = [ProductAddFeatureDependencies::class]
)
abstract class FeatureProductAddComponent : DiComponent {

	abstract fun fabric(): MultiViewModelFactory

	@PerFeature
	@Component(dependencies = [NetworkApi::class])
	interface ProductFeatureAddDependenciesComponent : ProductAddFeatureDependencies
}