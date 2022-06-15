package com.ozontech.core_navigation_impl.di

import androidx.fragment.app.FragmentManager
import com.ozontech.core_network_impl.di.CoreNetworkComponent
import com.ozontech.core_network_impl.di.DaggerCoreNetworkComponent
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

class DIComponentStorage(private val fragmentManager: FragmentManager) {

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
			CoreNetworkComponent::class -> {
				DaggerCoreNetworkComponent.create()
			}
			FeatureProductComponent::class -> {
				DaggerFeatureProductComponent.builder()
					.productFeatureDependencies(
						DaggerFeatureProductComponent_ProductFeatureDependenciesComponent.builder()
							.networkApi(initAndGet(CoreNetworkComponent::class))
							.productNavigationApi(initAndGet(DaggerCoreNavigationComponent::class).getProductNavigation())
							.build()
					)
					.build()
			}
			FeaturePdpComponent::class -> {
				DaggerFeaturePdpComponent.builder()
					.pdpFeatureDependencies(
						DaggerFeaturePdpComponent_PdpDependenciesComponent.builder()
							.networkApi(initAndGet(CoreNetworkComponent::class))
							.build()
					).build()
			}
			FeatureProductAddComponent::class -> {
				DaggerFeatureProductAddComponent.builder()
					.productAddFeatureDependencies(
						DaggerFeatureProductAddComponent_ProductFeatureAddDependenciesComponent.builder()
							.networkApi(initAndGet(CoreNetworkComponent::class))
							.build()
					).build()
			}
			DaggerCoreNavigationComponent::class -> {
				DaggerCoreNavigationComponent.builder().fragmentManager(fragmentManager).build()
			}
			else -> throw Exception("cannot find component")
		} as T
	}
}


