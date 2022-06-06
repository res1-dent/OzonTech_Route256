package com.ozontech.homework2.data.db.models.products

object ProductsContract {

    const val TABLE_NAME = "products"

    object Columns {
        const val GUID = "guid"
        const val NAME = "name"
        const val PRICE = "price"
        const val DESCRIPTION = "description"
        const val RATING = "rating"
        const val IS_FAVORITE = "isFavorite"
        const val IS_IN_CART = "isInCart"
        const val IMAGES = "image"
        const val WEIGHT = "weight"
        const val COUNT = "count"
        const val AVAILABLE_COUNT = "availableCount"
        const val ADDITIONAL_PARAMS = "additionalParams"
        const val COUNTER = "counter"
    }
}