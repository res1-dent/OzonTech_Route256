package com.ozontech.homework2.data.db.models.productsInList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = ProductsInListContract.TABLE_NAME,
)
data class ProductsInListDB(
    @PrimaryKey
    @ColumnInfo(name = ProductsInListContract.Columns.GUID)
    val guid: String,
    @ColumnInfo(name = ProductsInListContract.Columns.IMAGE)
    val image: String,
    @ColumnInfo(name = ProductsInListContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = ProductsInListContract.Columns.PRICE)
    val price: String,
    @ColumnInfo(name = ProductsInListContract.Columns.RATING)
    val rating: Float,
    @ColumnInfo(name = ProductsInListContract.Columns.IS_FAVORITE)
    val isFavorite: Boolean,
    @ColumnInfo(name = ProductsInListContract.Columns.IS_IN_CART)
    val isInCart: Boolean,
)
