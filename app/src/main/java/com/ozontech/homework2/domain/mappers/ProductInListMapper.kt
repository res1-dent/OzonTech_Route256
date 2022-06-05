package com.ozontech.homework2.domain.mappers

import com.ozontech.homework2.data.dto.ProductInList
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO

fun ProductInListDO.toVO() = ProductInListVO(
    guid,
    image,
    name,
    price,
    rating.toFloat()
)