package com.ozontech.homework2.di

import android.app.Application
import com.ozontech.homework2.data.storage.CounterPreference
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun providesDataStore(app: Application): CounterPreference {
        return CounterPreference(app)
    }

}