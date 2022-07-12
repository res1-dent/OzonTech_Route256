package com.ozontech.feature_products_impl.domain.view_objects

import androidx.annotation.StringRes
import com.ozontech.core_utils.custom_views.CartButtonSimple
import kotlin.random.Random

sealed class ProductInListRecyclerViewModel {
	data class ProductInListVO(
		val guid: String,
		val image: List<String>,
		val name: String,
		val price: Int,
		val rating: Float,
		val counter: String,
		val cartButtonState: Boolean?,
	) : ProductInListRecyclerViewModel()

	data class Header(@StringRes val text: Int) :
		ProductInListRecyclerViewModel()
}
