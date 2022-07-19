package com.ozontech.core_navigation_impl.di

import com.ozontech.core_navigation_api.NavigationApi
import com.ozontech.core_navigation_impl.navigation.FragmentManagerHost
import com.ozontech.core_utils.di.DiComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface CoreNavigationComponent : NavigationApi, DiComponent {
	fun getFMHost(): FragmentManagerHost
}

@Module
class CoreNavigationModule {
	@Provides
	@Singleton
	fun providesFMHost(): FragmentManagerHost = FragmentManagerHost()
}
