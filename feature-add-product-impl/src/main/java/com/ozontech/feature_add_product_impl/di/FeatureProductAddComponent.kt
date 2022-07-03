package com.ozontech.feature_add_product_impl.di

import com.ozontech.core_database_api.DatabaseApi
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

	abstract fun getFabric(): MultiViewModelFactory

	@PerFeature
	@Component(dependencies = [DatabaseApi::class])
	interface ProductFeatureAddDependenciesComponent : ProductAddFeatureDependencies
}