package com.ozontech.feature_products_impl.presentation.adapter

import com.ozontech.feature_products_impl.databinding.HeaderListItemBinding
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel

class HeaderHolder(
	private val binding: HeaderListItemBinding,
) : BaseHolder<ProductInListRecyclerViewModel.Header>(binding.root) {

	override fun bindModel(item: ProductInListRecyclerViewModel.Header) {
		binding.root.text = item.text
	}
}