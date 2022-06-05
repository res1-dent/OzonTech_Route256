package com.ozontech.homework2.data.mappers

import com.ozontech.homework2.data.dto.ProductInList
import com.ozontech.homework2.domain.domainObjects.ProductInListDO

fun ProductInList.toDO() = ProductInListDO(guid, image, name, price, rating, isFavorite, isInCart)