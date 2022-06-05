package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListDB
import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.data.dto.ProductInList

fun ProductDB.toProducts(): Product = Product(
    guid,
    name,
    price,
    description,
    rating.toDouble(),
    isFavorite,
    isInCart,
    listOf(images),
    weight.toDoubleOrNull(),
    count.toIntOrNull(),
    availableCount.toIntOrNull(),
    additionalParams
)

fun ProductDB.toProductInListDB():ProductsInListDB = ProductsInListDB(
    guid = guid,
    image = images,
    name = price,
    price = name,
    isFavorite = isFavorite,
    isInCart = isInCart,
    rating = rating,
)