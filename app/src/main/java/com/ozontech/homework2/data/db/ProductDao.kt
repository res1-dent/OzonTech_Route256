package com.ozontech.homework2.data.db

import androidx.room.*
import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.db.models.products.ProductsContract
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM ${ProductsContract.TABLE_NAME}")
    fun getAllProducts(): Flow<List<ProductDB>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductDB(products: List<ProductDB>)

    @Query("select * from ${ProductsContract.TABLE_NAME} where ${ProductsContract.Columns.GUID} = :guid")
    fun getProductByGuid(guid: String): ProductDB

    @Query("select ${ProductsContract.Columns.COUNTER} from ${ProductsContract.TABLE_NAME} where ${ProductsContract.Columns.GUID} = :guid")
    fun getCounterByGuid(guid: String): Int

    @Update
    fun updateCounter(product: ProductDB)



}