package com.ozontech.feature_products_impl.domain.mappers

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel

fun ProductInListDO.toVO() =
	ProductInListRecyclerViewModel.ProductInListVO(
		guid = guid,
		image = image,
		name = name,
		price = price.toInt(),
		rating = rating.toFloat(),
		counter = counter.toString(),
		isInCart = isInCart
	)