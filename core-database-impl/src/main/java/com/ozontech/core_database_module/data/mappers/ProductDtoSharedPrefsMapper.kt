package com.ozontech.core_database_module.data.mappers

import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInCartSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs

fun ProductDtoSharedPrefs.toProductInListDroSharedPrefs(): ProductInListDtoSharedPrefs =
	ProductInListDtoSharedPrefs(
		guid = guid,
		image = images,
		name = name,
		price = price,
		rating = rating,
		isFavorite = isFavorite,
		isInCart = isInCart,
		counter = 0
	)


fun ProductInListDtoSharedPrefs.toProductInCart(): ProductInCartSharedPrefs = ProductInCartSharedPrefs(
	id = guid, imageUlr = image.first(), name = name, price = price, amount = 1)
