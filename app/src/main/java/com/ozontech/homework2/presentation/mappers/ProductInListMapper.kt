package com.ozontech.homework2.presentation.mappers

import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO

fun ProductInListDO.toVO() = ProductInListVO(
    guid = guid,
    image = image,
    name = name,
    price = price.toInt(),
    rating = rating.toFloat(),
    counter = counter.toString()
)