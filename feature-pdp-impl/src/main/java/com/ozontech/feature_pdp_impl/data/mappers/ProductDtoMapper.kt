package com.ozontech.feature_pdp_impl.data.mappers

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_network_api.models.ProductDto
import com.ozontech.feature_pdp_impl.domain.domain_objects.ProductDO

fun ProductDto.toDO(): ProductDO = ProductDO(
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
	additionalParams = additionalParams,
)

fun ProductDtoSharedPrefs.toDO(): ProductDO = ProductDO(
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
