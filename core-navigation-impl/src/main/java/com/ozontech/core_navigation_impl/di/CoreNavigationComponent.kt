package com.ozontech.core_navigation_impl.di

import com.ozontech.core_navigation_api.NavigationApi
import com.ozontech.core_utils.di.DiComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface CoreNavigationComponent: NavigationApi, DiComponent
