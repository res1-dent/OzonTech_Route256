package com.ozontech.feature_cart_impl.data.mappers

import com.ozontech.core_database_api.models.ProductInCartSharedPrefs
import com.ozontech.feature_cart_impl.domain.view_object.Product

fun ProductInCartSharedPrefs.toProduct(): Product = Product(
	id, name, imageUlr, price.toInt(), amount
)