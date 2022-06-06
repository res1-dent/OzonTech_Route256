package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.domain.domainObjects.ProductDO

fun Product.toDO(): ProductDO = ProductDO(
    guid = guid,
    name = name,
    price = price,
    description = description,
    rating = rating,
    isFavorite = isFavorite,
    isInCart = isInCart,
    images = images,
    weight = weight,
    count = count,
    availableCount = availableCount,
    additionalParams = additionalParams,
    counter = counter
)

fun Product.toProduct(): ProductDB = ProductDB(
    guid = guid,
    images = images.first(),
    name = name,
    price = price,
    rating = rating.toFloat(),
    isFavorite = isFavorite,
    isInCart = isInCart,
    description = description,
    weight = weight.toString(),
    availableCount = availableCount.toString(),
    count = count.toString(),
    additionalParams = additionalParams

)