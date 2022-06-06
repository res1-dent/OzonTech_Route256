package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.domain.domainObjects.ProductDO

fun Product.toDO(): ProductDO = ProductDO(
    guid,
    name,
    price,
    description,
    rating,
    isFavorite,
    isInCart,
    images,
    weight,
    count,
    availableCount,
    additionalParams,
    counter
)

fun Product.toProduct(): ProductDB = ProductDB(
    guid,
    images.first(),
    name,
    price,
    rating.toFloat(),
    isFavorite,
    isInCart,
    description,
    weight.toString(),
    availableCount.toString(),
    count.toString(),
    additionalParams

)