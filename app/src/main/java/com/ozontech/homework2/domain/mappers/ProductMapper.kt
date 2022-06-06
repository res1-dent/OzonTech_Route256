package com.ozontech.homework2.domain.mappers

import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.presentation.viewObjects.ProductVO

fun ProductDO.toVO(): ProductVO = ProductVO(
    guid,
    name,
    price.toInt(),
    description,
    rating.toFloat(),
    isFavorite,
    isInCart,
    images,
    weight,
    count,
    availableCount,
    additionalParams,
    counter
)