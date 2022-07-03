package com.ozontech.feature_products_impl.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ozontech.core_utils.adapters.ImagesAdapter
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_impl.R
import com.ozontech.feature_products_impl.databinding.HeaderListItemBinding
import com.ozontech.feature_products_impl.databinding.ProductListItemBinding
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

class ProductsAdapter(
	private val onProductClick: (String) -> Unit,
	private val addToCart: (String, Boolean) -> Unit,
	private val lifecycleScope: CoroutineScope
) : ListAdapter<ProductInListRecyclerViewModel, BaseHolder<*>>(
	ProductsDiffUtilCallback()
) {

	private val nestedRecyclerViewPool = RecyclerView.RecycledViewPool().apply {
		this.setMaxRecycledViews(R.layout.product_list_item, 25)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<*> {
		return when (viewType) {
			R.layout.product_list_item -> ProductsHolder(
				parent.inflate(
					ProductListItemBinding::inflate
				), onProductClick, addToCart
			)
			R.layout.header_list_item -> HeaderHolder(
				parent.inflate(HeaderListItemBinding::inflate)
			)
			else -> error("No holder for this ViewType")
		}
	}

	override fun onBindViewHolder(holder: BaseHolder<*>, position: Int) {
		holder.bind(currentList[position])
	}

	override fun getItemViewType(position: Int): Int {
		return when (currentList[position]) {
			is ProductInListRecyclerViewModel.ProductInListVO -> R.layout.product_list_item
			is ProductInListRecyclerViewModel.Header -> R.layout.header_list_item
		}
	}

	inner class ProductsHolder(
		private val binding: ProductListItemBinding,
		private val onProductClick: (String) -> Unit,
		private val addToCart: (String, Boolean) -> Unit,
	) : BaseHolder<ProductInListRecyclerViewModel.ProductInListVO>(binding.root) {

		private val currentItem
			get() = currentList[absoluteAdapterPosition] as ProductInListRecyclerViewModel.ProductInListVO

		private val imagesAdapter = ImagesAdapter() {
			currentItem.guid.let(onProductClick)
		}

		init {
			binding.productImage.setRecycledViewPool(nestedRecyclerViewPool)
			binding.productImage.adapter = imagesAdapter
			PagerSnapHelper().attachToRecyclerView(binding.productImage)

			binding.root.clicks().onEach {
				currentItem.guid.let(onProductClick)
			}.launchIn(lifecycleScope)

			binding.addToCartButton.changeStateSharedFlow.onEach {
				currentItem.let {
					addToCart(it.guid, it.isInCart.not())
				}
			}.launchIn(lifecycleScope)
		}

		override fun bindModel(item: ProductInListRecyclerViewModel.ProductInListVO) {
			with(binding) {
				imagesAdapter.submitList(item.image)
				binding.addToCartButton.isInCart = item.isInCart
				nameTextView.text = item.name
				priceTextView.text = binding.root.resources.getString(R.string.price, item.price)
				ratingRatingBar.rating = item.rating
				counterTextView.text = item.counter
			}
		}
	}

	class ProductsDiffUtilCallback : DiffUtil.ItemCallback<ProductInListRecyclerViewModel>() {

		override fun areItemsTheSame(
			oldItem: ProductInListRecyclerViewModel,
			newItem: ProductInListRecyclerViewModel
		): Boolean {
			return when {
				oldItem is ProductInListRecyclerViewModel.ProductInListVO
						&& newItem is ProductInListRecyclerViewModel.ProductInListVO ->
					oldItem.guid == newItem.guid
				oldItem is ProductInListRecyclerViewModel.Header
						&& newItem is ProductInListRecyclerViewModel.Header ->
					oldItem::class.java == newItem::class.java
				else -> false
			}
		}

		override fun areContentsTheSame(
			oldItem: ProductInListRecyclerViewModel,
			newItem: ProductInListRecyclerViewModel
		): Boolean {
			return when {
				oldItem is ProductInListRecyclerViewModel.ProductInListVO
						&& newItem is ProductInListRecyclerViewModel.ProductInListVO -> {
					if (oldItem.image != newItem.image) return false
					if (oldItem.name != newItem.name) return false
					if (oldItem.price != newItem.price) return false
					if (oldItem.rating != newItem.rating) return false
					if (oldItem.counter != newItem.counter) return false
					if (oldItem.isInCart != newItem.isInCart) return false
					true
				}
				oldItem is ProductInListRecyclerViewModel.Header
						&& newItem is ProductInListRecyclerViewModel.Header ->
					oldItem.text == newItem.text
				else -> false
			}
		}

		override fun getChangePayload(
			oldItem: ProductInListRecyclerViewModel,
			newItem: ProductInListRecyclerViewModel
		): Any? {
			return if (oldItem is ProductInListRecyclerViewModel.ProductInListVO
				&& newItem is ProductInListRecyclerViewModel.ProductInListVO
			) {
				oldItem.counter == newItem.counter
			} else true
		}
	}
}