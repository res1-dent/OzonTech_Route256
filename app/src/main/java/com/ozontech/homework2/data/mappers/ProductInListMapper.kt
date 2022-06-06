package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.dto.ProductInList
import com.ozontech.homework2.domain.domainObjects.ProductInListDO

fun ProductInList.toDO() = ProductInListDO(
    guid = guid,
    image = image,
    name = name,
    price = price,
    rating = rating,
    isFavorite = isFavorite,
    isInCart = isInCart,
    counter = counter
)