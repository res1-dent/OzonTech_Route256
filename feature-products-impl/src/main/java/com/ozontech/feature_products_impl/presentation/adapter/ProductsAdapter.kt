package com.ozontech.feature_products_impl.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozontech.core_utils.R
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_impl.databinding.ProductListItemBinding
import com.ozontech.feature_products_impl.presentation.view_objects.ProductInListVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.recyclerview.FlingEvent

class ProductsAdapter(
	private val onProductClick: (String) -> Unit,
	private val lifecycleScope: CoroutineScope
) : ListAdapter<ProductInListVO, ProductsAdapter.Holder>(
	ProductsDiffUtilCallback()
) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(parent.inflate(ProductListItemBinding::inflate), onProductClick)
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.bind(currentList[position])
	}

	inner class Holder(
		private val binding: ProductListItemBinding,
		private val onProductClick: (String) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			binding.root.clicks().onEach {
				currentList[absoluteAdapterPosition].guid.let(onProductClick)
			}.launchIn(lifecycleScope)
		}

		fun bind(item: ProductInListVO) {
			with(binding) {
				Glide.with(itemView).load(item.image).into(productImage)
				nameTextView.text = item.name
				priceTextView.text = binding.root.resources.getString(R.string.price, item.price)
				ratingRatingBar.rating = item.rating
				counterTextView.text = item.counter
			}
		}
	}

	class ProductsDiffUtilCallback : DiffUtil.ItemCallback<ProductInListVO>() {

		override fun areItemsTheSame(oldItem: ProductInListVO, newItem: ProductInListVO): Boolean {
			return oldItem.guid == newItem.guid
		}

		override fun areContentsTheSame(
			oldItem: ProductInListVO,
			newItem: ProductInListVO
		): Boolean {
			if (oldItem.image != newItem.image) return false
			if (oldItem.name != newItem.name) return false
			if (oldItem.price != newItem.price) return false
			if (oldItem.rating != newItem.rating) return false
			if (oldItem.counter != newItem.counter) return false
			return true
		}
	}
}