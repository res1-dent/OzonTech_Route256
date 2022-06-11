package com.ozontech.feature_pdp_impl.presentation.mappers

import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO
import com.ozontech.feature_pdp_impl.presentation.view_objects.ProductVO

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