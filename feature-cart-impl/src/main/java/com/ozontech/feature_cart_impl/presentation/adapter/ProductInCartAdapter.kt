package com.ozontech.feature_cart_impl.presentation.adapter

import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozontech.core_utils.inflate
import com.ozontech.feature_cart_impl.R
import com.ozontech.feature_cart_impl.databinding.ListItemCartBinding
import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductInCartAdapter(
	private val onClick: (String, Int) -> Unit,
	private val lifecycleScope: CoroutineScope
) : ListAdapter<Product, ProductInCartAdapter.ProductInCartViewHolder>(ProductInCartDiffUtilCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInCartViewHolder {
		return ProductInCartViewHolder(parent.inflate(ListItemCartBinding::inflate), onClick)
	}

	override fun onBindViewHolder(holder: ProductInCartViewHolder, position: Int) {
		holder.bind(currentList[position])
	}

	override fun onBindViewHolder(
		holder: ProductInCartViewHolder,
		position: Int,
		payloads: MutableList<Any>
	) {
		if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
		else {
			payloads.forEach { payload->
				if (payload == true) holder.bindAmount(currentList[position].amount)
				else super.onBindViewHolder(holder, position, payloads)
			}
		}
	}

	override fun getItemCount(): Int {
		return currentList.size
	}

	fun getGuidByPosition(positionForRemove: Int): String {
		return currentList[positionForRemove].id
	}

	class ProductInCartDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
		override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
			if (oldItem.imageUlr != newItem.imageUlr) return false
			if (oldItem.name != newItem.name) return false
			if (oldItem.price != newItem.price) return false
			if (oldItem.amount != newItem.amount) return false
			return true
		}

		override fun getChangePayload(oldItem: Product, newItem: Product): Any? {
			return oldItem.amount != newItem.amount
		}
	}

	inner class ProductInCartViewHolder(
		private val binding: ListItemCartBinding,
		 onClick: (String, Int) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			binding.addToCartButton.productsSharedFlow.onEach {
				if (absoluteAdapterPosition>=0)
				onClick(currentList[absoluteAdapterPosition].id, it)
			}.launchIn(lifecycleScope)
		}

		fun bind(product: Product) {
			with(binding){
				Glide.with(itemView).load(product.imageUlr).into(productImageView)
				productNameTextView.text = product.name
				amountTextView.text = binding.root.context.getString(com.ozontech.core_utils.R.string.price, product.price  )
				addToCartButton.setProductAmount(product.amount)
			}
		}
		fun bindAmount(amount: Int){
			binding.addToCartButton.setProductAmount(amount)
		}

	}
}