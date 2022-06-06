package com.ozontech.homework2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozontech.homework2.data.db.ProductsDatabase.Companion.DB_VERSION
import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Database(entities = [ProductDB::class], version = DB_VERSION)
abstract class ProductsDatabase : RoomDatabase() {


    abstract fun productDao(): ProductDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "ProductsDatabase"
    }

    suspend fun onCreate(products: List<ProductDB>) =
        withContext(Dispatchers.IO) {
            productDao().insertProductDB(products)
        }


}