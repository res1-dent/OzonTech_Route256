package com.ozontech.feature_products_impl.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel

abstract class BaseHolder<T : ProductInListRecyclerViewModel>(
	itemView: View
) : RecyclerView.ViewHolder(itemView) {

	fun bind(model: ProductInListRecyclerViewModel) {
		(model as? T)?.let {
			bindModel(it)
		}
	}
	abstract fun bindModel(item: T)

}