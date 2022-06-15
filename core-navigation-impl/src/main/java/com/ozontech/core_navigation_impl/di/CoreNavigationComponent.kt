package com.ozontech.core_navigation_impl.di

import androidx.fragment.app.FragmentManager
import com.ozontech.core_navigation_api.NavigationApi
import com.ozontech.core_utils.di.DiComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface CoreNavigationComponent : NavigationApi, DiComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun fragmentManager(fragmentManager: FragmentManager): Builder
		fun build(): CoreNavigationComponent
	}
}
