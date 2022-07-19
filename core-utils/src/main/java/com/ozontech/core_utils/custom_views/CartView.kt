package com.ozontech.core_utils.custom_views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.ozontech.core_utils.R
import com.ozontech.core_utils.databinding.CartLayoutBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.properties.Delegates

class CartView @JvmOverloads constructor(
	context: Context,
	private val attrs: AttributeSet? = null,
	private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

	private val binding: CartLayoutBinding =
		CartLayoutBinding.inflate(LayoutInflater.from(context), this)

	private var currentCount: Int = 0

	private var restOfProducts by Delegates.notNull<Int>()

	private val productsMutableSharedFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1)

	val productsSharedFlow = productsMutableSharedFlow.asSharedFlow()


	init {
		setListeners()
		setupViewWithAttrs()
	}

	fun setProductAmount(amount: Int) {
		if (amount != 0) {
			currentCount = amount
			toggleViewState(true)
			binding.counterTextView.text = currentCount.toString()
		} else {
			toggleViewState(false)
		}
	}

	private fun setListeners() {
		binding.cartButton.setOnClickListener(::onCardButtonClick)
		binding.minusButton.setOnClickListener(::decreaseCount)
		binding.plusButton.setOnClickListener(::incrementCount)
	}

	private fun setupViewWithAttrs() {
		context.withStyledAttributes(set = attrs, R.styleable.CartView, defStyleAttr) {
			restOfProducts = getInteger(R.styleable.CartView_max_products, 0)
		}
	}

	private fun incrementCount(v: View? = null) {
		productsMutableSharedFlow.tryEmit(1)
	}

	private fun decreaseCount(v: View? = null) {
		productsMutableSharedFlow.tryEmit(-1)
	}

	private fun onCardButtonClick(v: View) {
			incrementCount()
	}

	private fun toggleViewState(expanded: Boolean) {
		binding.expandedView.isVisible = expanded
		binding.cartButton.isVisible = expanded.not()
	}

}