package com.ozontech.feature_products_impl.di

import android.app.Application
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.MultiViewModelFactory
import com.ozontech.core_utils.di.PerFeature
import com.ozontech.feature_products_api.ProductNavigationApi
import com.ozontech.feature_products_impl.presentation.view.ProductListFragment
import dagger.BindsInstance
import dagger.Component

@PerFeature
@Component(
	modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
	dependencies = [ProductFeatureDependencies::class]
)
abstract class FeatureProductComponent : DiComponent {

	abstract fun getFabric(): MultiViewModelFactory
	abstract fun inject(fragment: ProductListFragment)


	@Component.Builder
	interface Builder {
		@BindsInstance
		fun context(context: Application): Builder
		fun dependencies(deps: ProductFeatureDependencies): Builder
		fun build(): FeatureProductComponent
	}

	@PerFeature
	@Component(dependencies = [ProductNavigationApi::class, DatabaseApi::class, NetworkApi::class])
	interface ProductFeatureDependenciesComponent : ProductFeatureDependencies

}


