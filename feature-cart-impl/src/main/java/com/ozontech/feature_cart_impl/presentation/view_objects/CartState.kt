package com.ozontech.feature_cart_impl.presentation.view_objects

import com.ozontech.feature_cart_impl.domain.view_object.Product

sealed class CartState {
	object Init : CartState()
	object Empty : CartState()
	data class Success(val products: List<Product>, val totalPrice: Int): CartState()
	data class Error(val message: String): CartState()
}
