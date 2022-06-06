package com.ozontech.homework2.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ozontech.homework2.data.db.ProductDao
import com.ozontech.homework2.data.db.ProductsDatabase
import com.ozontech.homework2.data.mappers.toProduct
import com.ozontech.homework2.data.mappers.toProductInListDB
import com.ozontech.homework2.utils.parseJsonToProduct
import com.ozontech.homework2.utils.parseJsonToProductList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    private lateinit var database: ProductsDatabase

    @Provides
    @Singleton
    fun providesDataBase(application: Application): ProductsDatabase {
        database = Room
            .databaseBuilder(application, ProductsDatabase::class.java, ProductsDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.e("DataBase", "Create")
                    CoroutineScope(Dispatchers.IO).launch {
                        val products = parseJsonToProduct(application).map { it.toProduct() }
                        database.onCreate(products)
                    }
                }
            })
            .build()
        return database
    }

    @Provides
    @Singleton
    fun providesProductDao(database: ProductsDatabase): ProductDao {
        return database.productDao()
    }
}