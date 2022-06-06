package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.db.models.productsInList.ProductsInListDB
import com.ozontech.homework2.data.dto.ProductInList

fun ProductsInListDB.toProductInList(): ProductInList = ProductInList(
    guid = guid,
    image = image,
    name = name,
    price = price,
    isFavorite = isFavorite,
    isInCart = isInCart,
    rating = rating.toDouble(),
    counter = counter
)