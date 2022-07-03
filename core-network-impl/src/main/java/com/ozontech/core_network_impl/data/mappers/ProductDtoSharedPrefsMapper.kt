package com.ozontech.core_network_impl.data.mappers

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_network_api.models.ProductDto

fun ProductDtoSharedPrefs.toProductDto(): ProductDto {
	return ProductDto(
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
		additionalParams = additionalParams
	)
}