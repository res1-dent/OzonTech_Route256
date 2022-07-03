package com.ozontech.feature_products_impl.presentation.adapter.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductItemDecorator(
	private val offsetInPx: Int
) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	) {
		super.getItemOffsets(outRect, view, parent, state)
		with(outRect) {
			bottom = offsetInPx
			left = offsetInPx
			right = offsetInPx
		}
	}
}