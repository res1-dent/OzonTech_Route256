package com.ozontech.core_database_module.di

import android.content.Context
import com.ozontech.core_database_api.DatabaseApi
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_database_module.data.ProductDatabaseImpl
import com.ozontech.core_utils.di.DiComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
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

