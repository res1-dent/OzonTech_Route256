package com.ozontech.homework2.di

import android.content.Context
import androidx.work.WorkManager
import com.ozontech.core_database_module.di.CoreDatabaseComponent
import com.ozontech.core_database_module.di.DaggerCoreDatabaseComponent
import com.ozontech.core_navigation_impl.di.CoreNavigationComponent
import com.ozontech.core_navigation_impl.di.DaggerCoreNavigationComponent
import com.ozontech.core_network_impl.di.CoreNetworkComponent
import com.ozontech.core_network_impl.di.DaggerCoreNetworkComponent
import com.ozontech.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.ozontech.core_utils.di.DiComponent
import com.ozontech.feature_add_product_impl.di.DaggerFeatureProductAddComponent
import com.ozontech.feature_add_product_impl.di.DaggerFeatureProductAddComponent_ProductFeatureAddDependenciesComponent
import com.ozontech.feature_add_product_impl.di.FeatureProductAddComponent
import com.ozontech.feature_pdp_impl.di.DaggerFeaturePdpComponent
import com.ozontech.feature_pdp_impl.di.DaggerFeaturePdpComponent_PdpDependenciesComponent
import com.ozontech.feature_pdp_impl.di.FeaturePdpComponent
import com.ozontech.feature_products_impl.di.DaggerFeatureProductComponent
import com.ozontech.feature_products_impl.di.DaggerFeatureProductComponent_ProductFeatureDependenciesComponent
import com.ozontech.feature_products_impl.di.FeatureProductComponent
import kotlin.reflect.KClass

class DIComponentStorageImpl(private val context: Context) {

	private val map: MutableMap<KClass<*>, Any> = mutableMapOf()

	@Suppress("UNCHECKED_CAST")
	@Synchronized
	fun <T : DiComponent> initAndGet(component: KClass<T>): T {
		if (!map.containsKey(component)) {
			map[component] = createComponent(component)
		}
		return map[component] as T
	}

	@Synchronized
	fun <T : DiComponent> release(component: KClass<T>) {
		map.remove(component)
	}

	@Suppress("UNCHECKED_CAST")
	private fun <T : DiComponent> createComponent(component: KClass<T>): T {
		return when (component) {
			CoreDatabaseComponent::class -> {
				DaggerCoreDatabaseComponent.builder().context(context).build()
			}
			CoreNetworkComponent::class -> {
				DaggerCoreNetworkComponent.builder().workManager(WorkManager.getInstance(context))
					.dependencies(
						DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
							.databaseApi(initAndGet(CoreDatabaseComponent::class))
							.build()
					)
					.build()
			}
			FeatureProductComponent::class -> {
				DaggerFeatureProductComponent.builder()
					.productFeatureDependencies(
						DaggerFeatureProductComponent_ProductFeatureDependenciesComponent.builder()
							.productNavigationApi(initAndGet(CoreNavigationComponent::class).getProductNavigation())
							.databaseApi(initAndGet(CoreDatabaseComponent::class))
							.build()
					)
					.build()
			}
			FeaturePdpComponent::class -> {
				DaggerFeaturePdpComponent.builder()
					.pdpFeatureDependencies(
						DaggerFeaturePdpComponent_PdpDependenciesComponent.builder()
							.databaseApi(initAndGet(CoreDatabaseComponent::class))
							.build()
					).build()
			}
			FeatureProductAddComponent::class -> {
				DaggerFeatureProductAddComponent.builder()
					.productAddFeatureDependencies(
						DaggerFeatureProductAddComponent_ProductFeatureAddDependenciesComponent.builder()
							.databaseApi(initAndGet(CoreDatabaseComponent::class))
							.build()
					).build()
			}
			CoreNavigationComponent::class -> {
				DaggerCoreNavigationComponent.create()
			}
			else -> throw Exception("cannot find component ${component::class}")
		} as T
	}
}




