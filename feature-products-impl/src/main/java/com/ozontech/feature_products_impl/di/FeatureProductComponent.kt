package com.ozontech.feature_products_impl.di

import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.MultiViewModelFactory
import com.ozontech.core_utils.di.PerFeature
import com.ozontech.feature_products_api.ProductNavigationApi
import com.ozontech.feature_products_impl.presentation.view.ProductListFragment
import dagger.Component

@PerFeature
@Component(
	modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
	dependencies = [ProductFeatureDependencies::class]
)
abstract class FeatureProductComponent : DiComponent {

	abstract fun getFabric(): MultiViewModelFactory
	abstract fun inject(fragment: ProductListFragment)

	@PerFeature
	@Component(dependencies = [NetworkApi::class, ProductNavigationApi::class, DatabaseApi::class])
	interface ProductFeatureDependenciesComponent : ProductFeatureDependencies

}


