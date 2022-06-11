package com.ozontech.core_network_impl.data.mappers

import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.core_network_api.models.ProductInListDto

fun ProductDto.toProductInList(): ProductInListDto {
	return ProductInListDto(
		guid = guid,
		image = images.first(),
		name = name,
		price = price,
		rating = rating,
		isFavorite = isFavorite,
		isInCart = isInCart,
	)
}