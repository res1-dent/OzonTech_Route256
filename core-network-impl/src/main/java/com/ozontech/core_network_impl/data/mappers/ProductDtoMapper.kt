package com.ozontech.core_network_impl.data.mappers

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto


fun ProductDto.toProductDtoSharedPrefs(): ProductDtoSharedPrefs {
	return ProductDtoSharedPrefs(
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

fun ProductInListDto.toProductInListDtoSharedPrefs(): ProductInListDtoSharedPrefs {
	return ProductInListDtoSharedPrefs(
		guid = guid,
		image = image,
		name = name,
		price = price,
		rating = rating,
		isFavorite = isFavorite,
		isInCart = isInCart,
		counter = counter
	)
}

