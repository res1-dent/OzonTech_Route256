package com.ozontech.homework2.presentation.mappers

import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.presentation.viewObjects.ProductVO

fun ProductDO.toVO(): ProductVO = ProductVO(
    guid = guid,
    name = name,
    price = price.toInt(),
    description = description,
    rating = rating.toFloat(),
    isFavorite = isFavorite,
    isInCart = isInCart,
    images = images,
    weight = weight ?: 0.0,
    count = count ?: 0,
    availableCount = availableCount ?: 0,
    additionalParams = additionalParams,
)