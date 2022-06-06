package com.ozontech.homework2.data.db.models.productsInList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.COUNTER
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.GUID
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IMAGES
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IS_FAVORITE
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.IS_IN_CART
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.NAME
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.PRICE
import com.ozontech.homework2.data.db.models.products.ProductsContract.Columns.RATING
@Entity
data class ProductsInListDB(
    @PrimaryKey
    @ColumnInfo(name = GUID)
    val guid: String,
    @ColumnInfo(name = IMAGES)
    val image: String,
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
    @ColumnInfo(name = COUNTER)
    val counter: Int = 0
)
