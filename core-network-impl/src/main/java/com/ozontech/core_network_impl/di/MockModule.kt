package com.ozontech.core_network_impl.di

import com.ozontech.core_network_impl.mock.MockRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockModule {

	@Provides
	@Singleton
	fun providesMockRepository(): MockRepository = MockRepository()
}