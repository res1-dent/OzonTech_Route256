package com.ozontech.feature_pdp_impl.di

import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.core_utils.di.MultiViewModelFactory
import com.ozontech.core_utils.di.PerFeature
import com.ozontech.feature_pdp_impl.presentation.view.PdpFragment
import dagger.Component

@PerFeature
@Component(
	modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
	dependencies = [PdpFeatureDependencies::class]
)
abstract class FeaturePdpComponent: DiComponent {

	abstract fun inject(fragment: PdpFragment)
	abstract fun getFabric(): MultiViewModelFactory

	@PerFeature
	@Component(dependencies = [NetworkApi::class, DatabaseApi::class])
	interface PdpDependenciesComponent : PdpFeatureDependencies

}

