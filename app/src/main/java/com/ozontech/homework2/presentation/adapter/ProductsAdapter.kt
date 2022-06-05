package com.ozontech.homework2.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozontech.homework2.databinding.ProductListItemBinding
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import com.ozontech.homework2.utils.inflate

class ProductsAdapter(
private val onProductClick : (String) -> Unit
) : ListAdapter<ProductInListVO, ProductsAdapter.Holder>(ProductsDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(ProductListItemBinding::inflate), onProductClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currentList[position])
    }

    class Holder(
        private val binding: ProductListItemBinding,
        private val onProductClick : (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentProduct: String? = null

        init {
            binding.root.setOnClickListener {
                currentProduct?.let(onProductClick)
            }
        }

        fun bind(item: ProductInListVO) {
            currentProduct = item.guid
            Glide.with(itemView).load(item.image).into(binding.imageProductList)
            with(binding) {
                name.text = item.name
                price.text = item.price
                rating.rating = item.rating
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
            return oldItem == newItem
        }
    }


}