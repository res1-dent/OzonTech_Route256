package com.ozontech.core_database_module.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_database_module.data.ProductDatabaseImpl
import com.ozontech.core_utils.di.DiComponent
import dagger.*
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, SerializerModule::class])
@Singleton
interface CoreDatabaseComponent : DatabaseApi, DiComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun context(context: Context): Builder
		fun build(): CoreDatabaseComponent
	}

}

@Module
interface DatabaseModule {
	@Binds
	@Singleton
	fun bindDatabase(impl: ProductDatabaseImpl): ProductsDatabase
}

@Module
class SerializerModule {
	@Singleton
	@Provides
	fun providesGson(): Gson = GsonBuilder().create()
}



