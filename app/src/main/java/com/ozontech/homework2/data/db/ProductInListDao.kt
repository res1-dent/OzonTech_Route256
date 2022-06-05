package com.ozontech.homework2.data.db

import androidx.room.*
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListContract
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductInListDao {

    @Transaction
    @Query("SELECT * FROM ${ProductsInListContract.TABLE_NAME}")
    fun getAllProducts(): Flow<List<ProductsInListDB>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductInList(products: List<ProductsInListDB>)

}