package com.ozontech.homework2.data.db.models.productsInList

object ProductsInListContract {

    const val TABLE_NAME = "productsInList"
    object Columns {
        const val ID = "ID"
        const val GUID = "guid"
        const val IMAGE = "image"
        const val NAME = "name"
        const val PRICE = "price"
        const val RATING = "rating"
        const val IS_FAVORITE = "isFavorite"
        const val IS_IN_CART = "isInCart"
    }
}