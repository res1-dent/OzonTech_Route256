package com.ozontech.core_network_impl.data.mappers

import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
import com.ozontech.core_network_api.models.ProductInListDto

fun ProductInListDtoSharedPrefs.toProductInListDto(): ProductInListDto {
	return ProductInListDto(
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