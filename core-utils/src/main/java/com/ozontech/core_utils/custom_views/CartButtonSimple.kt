package com.ozontech.core_utils.custom_views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.ozontech.core_utils.databinding.CartButtonInListBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CartButtonSimple @JvmOverloads constructor(
	context: Context,
	private val attrs: AttributeSet? = null,
	private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

	private val binding: CartButtonInListBinding =
		CartButtonInListBinding.inflate(LayoutInflater.from(context), this)

	private val changeStateMutableSharedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
	val changeStateSharedFlow = changeStateMutableSharedFlow.asSharedFlow()

	var cartButtonState: Boolean? = false
		set(value) {
			field = value
			renderState()
		}

	init {
		renderState()
		setListeners()
	}

	private fun renderState() {
		toggleLoading(false)
		when (cartButtonState){
			null -> {
				binding.addToCartButton.text = "Закончился"
				binding.addToCartButton.backgroundTintList = ColorStateList.valueOf(Color.RED)
			}
			true -> {
				binding.addToCartButton.text = "В корзине"
				binding.addToCartButton.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
			}
			false -> {
				binding.addToCartButton.text = "В корзину"
				binding.addToCartButton.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
			}
		}
	}

	private fun toggleLoading(isLoading: Boolean) {
		binding.run {
			progressBar.isVisible = isLoading
			addToCartButton.isVisible = !isLoading
		}
	}

	private fun setListeners() {
		binding.addToCartButton.setOnClickListener {
			if (cartButtonState!=null){
				changeStateMutableSharedFlow.tryEmit(Unit)
				toggleLoading(true)
			}
		}
	}

}