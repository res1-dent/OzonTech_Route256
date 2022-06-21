package com.ozontech.feature_products_impl.presentation.mappers

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.presentation.view_objects.ProductInListVO

fun ProductInListDO.toVO() =
	ProductInListVO(
		guid = guid,
		image = image,
		name = name,
		price = price.toInt(),
		rating = rating.toFloat(),
		counter = counter.toString()
	)