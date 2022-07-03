package com.ozontech.core_navigation_impl.di

import com.ozontech.core_navigation_api.NavigationApi
import com.ozontech.core_utils.di.DiComponent
import dagger.Component

@Component(modules = [NavigationModule::class])
interface CoreNavigationComponent : NavigationApi, DiComponent {
}
