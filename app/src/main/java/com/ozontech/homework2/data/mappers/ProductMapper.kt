package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.data.dto.ProductInList
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
)

fun Product.toProductInList(): ProductInList = ProductInList(
    guid = guid,
    image = images.first(),
    name = name,
    price = price,
    rating = rating,
    isFavorite = isFavorite,
    isInCart = isInCart,
)
