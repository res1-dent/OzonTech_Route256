package com.ozontech.core_utils.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozontech.core_utils.databinding.ImageLayoutBinding
import com.ozontech.core_utils.inflate

class ImagesAdapter(
	private val onClick: (() -> Unit)? = null,
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

	private val differ = AsyncListDiffer(this, ImagesDiffUtilCallback())

	private val currentList
		get() = differ.currentList

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
		return ImageViewHolder(parent.inflate(ImageLayoutBinding::inflate), onClick)
	}

	override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
		holder.bind(currentList[position])
	}

	override fun getItemCount(): Int {
		return currentList.size
	}

	fun submitList(newList: List<String>) {
		differ.submitList(newList)
	}

	class ImagesDiffUtilCallback : DiffUtil.ItemCallback<String>() {
		override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
			return oldItem::class.java == newItem::class.java
		}

		override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
			return oldItem == newItem
		}

	}

	class ImageViewHolder(
		private val binding: ImageLayoutBinding,
		onClick: (() -> Unit)? = null
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			onClick?.let {
				binding.root.setOnClickListener {
					it()
				}
			}
		}

		fun bind(url: String) {
			Glide.with(itemView).load(url).into(binding.root)
		}

	}
}