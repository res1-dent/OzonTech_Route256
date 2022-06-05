package com.ozontech.homework2.data.db.models.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.TypeConverters
import com.ozontech.homework2.data.db.MapConverter
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListContract


@Entity(
    tableName = ProductsContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ProductDB::class,
            parentColumns = [ProductsContract.Columns.GUID],
            childColumns = [ProductsInListContract.Columns.GUID],
            onDelete = CASCADE
        )
    ],
    primaryKeys = [ProductsContract.Columns.GUID]
)
@TypeConverters(MapConverter::class)
data class ProductDB(
    @ColumnInfo(name = ProductsContract.Columns.GUID)
    val guid: String,
    @ColumnInfo(name = ProductsContract.Columns.IMAGES)
    val images: String,
    @ColumnInfo(name = ProductsContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = ProductsContract.Columns.PRICE)
    val price: String,
    @ColumnInfo(name = ProductsContract.Columns.RATING)
    val rating: Float,
    @ColumnInfo(name = ProductsContract.Columns.IS_FAVORITE)
    val isFavorite: Boolean,
    @ColumnInfo(name = ProductsContract.Columns.IS_IN_CART)
    val isInCart: Boolean,
    @ColumnInfo(name = ProductsContract.Columns.DESCRIPTION)
    val description: String,
    @ColumnInfo(name = ProductsContract.Columns.WEIGHT)
    val weight: String,
    @ColumnInfo(name = ProductsContract.Columns.AVAILABLE_COUNT)
    val availableCount: String,
    @ColumnInfo(name = ProductsContract.Columns.COUNT)
    val count: String,
    @ColumnInfo(name = ProductsContract.Columns.ADDITIONAL_PARAMS)
    val additionalParams: Map<String, String>,
    @ColumnInfo(name = ProductsContract.Columns.COUNTER)
    val counter: Int = 0
)
