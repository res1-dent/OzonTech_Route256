package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.dto.ProductDto
import com.ozontech.homework2.data.dto.ProductInListDto
import com.ozontech.homework2.domain.domainObjects.ProductDO

fun ProductDto.toDO(): ProductDO = ProductDO(
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

fun ProductDto.toProductInList(): ProductInListDto = ProductInListDto(
    guid = guid,
    image = images.first(),
    name = name,
    price = price,
    rating = rating,
    isFavorite = isFavorite,
    isInCart = isInCart,
)
