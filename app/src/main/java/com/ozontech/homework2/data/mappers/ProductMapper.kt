package com.ozontech.homework2.data.mappers

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
    additionalParams
)