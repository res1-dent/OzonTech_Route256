package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.db.models.productsInList.ProductsInListDB
import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.data.dto.ProductInList

fun ProductDB.toProducts(): Product = Product(
    guid = guid,
    name = name,
    price = price,
    description = description,
    rating = rating.toDouble(),
    isFavorite = isFavorite,
    isInCart = isInCart,
    images = listOf(images),
    weight = weight.toDoubleOrNull(),
    count = count.toIntOrNull(),
    availableCount = availableCount.toIntOrNull(),
    additionalParams = additionalParams,
    counter = counter
)