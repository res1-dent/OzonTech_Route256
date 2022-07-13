package com.ozontech.feature_cart_impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_cart_impl.R
import com.ozontech.feature_cart_impl.databinding.FragmentCartBinding
import com.ozontech.feature_cart_impl.di.FeatureCartComponent
import com.ozontech.feature_cart_impl.presentation.adapter.ProductInCartAdapter
import com.ozontech.feature_cart_impl.presentation.adapter.SwipeToDelete
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CartFragment : BaseFragment<FeatureCartComponent>(component = FeatureCartComponent::class) {

	private val binding by viewBinding(FragmentCartBinding::bind)

	private var restoreSnackbar: Snackbar? = null

	private val viewModel: CartViewModel by viewModels {
		currentComponent.getFabric()
	}

	private val adapter by autoCleared {
		ProductInCartAdapter(::updateCart, lifecycleScope)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initList()
		observeViewModelState()
		initSwipeToDelete()
	}

	private fun initSwipeToDelete() {
		val onItemSwipedToDelete = { positionForRemove: Int ->
			val guid = adapter.getGuidByPosition(positionForRemove)
			updateCart(guid, 0)
			showRestoreSnackBar(guid)
		}
		val swipeToDeleteCallback = SwipeToDelete(onItemSwipedToDelete)
		ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.cartRecyclerView)
	}

	private fun showRestoreSnackBar(guid: String) {
		restoreSnackbar = Snackbar.make(
			binding.root,
			R.string.restore_product_to_cart,
			Snackbar.LENGTH_SHORT
		).setAnchorView(binding.horizontalGuideLine)
			.setAction(R.string.yes) { updateCart(guid, 1) }
		restoreSnackbar?.show()
	}

	private fun updateCart(guid: String, count: Int) {
		viewLifecycleOwner.lifecycleScope.launch {
			viewModel.updateCart(guid, count)
		}
	}

	private fun observeViewModelState() {
		viewModel.listOfProductsStateFlow.onEach {
			adapter.submitList(it)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
		viewModel.totalPriceSharedFlow.onEach { totalPrice ->
			binding.allAmount.text = getString(R.string.total_price, totalPrice)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun initList() {
		with(binding.cartRecyclerView) {
			adapter = this@CartFragment.adapter
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return container?.inflate(FragmentCartBinding::inflate)?.root
	}

	override fun onDestroy() {
		super.onDestroy()
		restoreSnackbar?.dismiss()
	}
}