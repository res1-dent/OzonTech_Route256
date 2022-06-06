package com.ozontech.homework2.data.db.models.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.ozontech.homework2.data.db.MapConverter
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.ADDITIONAL_PARAMS
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.AVAILABLE_COUNT
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.COUNT
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.COUNTER
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.DESCRIPTION
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.GUID
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IMAGES
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IS_FAVORITE
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IS_IN_CART
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.NAME
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.PRICE
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.RATING
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.WEIGHT
import com.ozontech.homework2.data.db.models.products.ProductsContract.TABLE_NAME


@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [GUID]
)
@TypeConverters(MapConverter::class)
data class ProductDB(
    @ColumnInfo(name = GUID)
    val guid: String,
    @ColumnInfo(name = IMAGES)
    val images: String,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = PRICE)
    val price: String,
    @ColumnInfo(name = RATING)
    val rating: Float,
    @ColumnInfo(name = IS_FAVORITE)
    val isFavorite: Boolean,
    @ColumnInfo(name = IS_IN_CART)
    val isInCart: Boolean,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = WEIGHT)
    val weight: String,
    @ColumnInfo(name = AVAILABLE_COUNT)
    val availableCount: String,
    @ColumnInfo(name = COUNT)
    val count: String,
    @ColumnInfo(name = ADDITIONAL_PARAMS)
    val additionalParams: Map<String, String>,
    @ColumnInfo(name = COUNTER)
    val counter: Int = 0
)
