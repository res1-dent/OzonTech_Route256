package com.ozontech.core_network_impl.di

import com.ozontech.core_network_api.NetworkApi
import com.ozontech.core_utils.di.DiComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MockModule::class])
interface CoreNetworkComponent: NetworkApi, DiComponent
