package com.ozontech.feature_products_impl.data.mappers

import com.ozontech.core_network_api.models.ProductInListDto
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO

fun ProductInListDto.toDO() =
	ProductInListDO(
		guid = guid,
		image = image,
		name = name,
		price = price,
		rating = rating,
		isFavorite = isFavorite,
		isInCart = isInCart,
		counter = counter
	)