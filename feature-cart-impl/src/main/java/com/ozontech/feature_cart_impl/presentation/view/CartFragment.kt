package com.ozontech.feature_cart_impl.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.transition.TransitionManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialFade
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_cart_impl.R
import com.ozontech.feature_cart_impl.databinding.FragmentCartBinding
import com.ozontech.feature_cart_impl.di.FeatureCartComponent
import com.ozontech.feature_cart_impl.presentation.adapter.ProductInCartAdapter
import com.ozontech.feature_cart_impl.presentation.adapter.SwipeToDelete
import com.ozontech.feature_cart_impl.presentation.view_objects.CartState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.android.view.clicks

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
		initListeners()
	}

	private fun initListeners() {
		binding.goToProductsButton.clicks().onEach {
			parentFragmentManager.popBackStack()
		}.launchIn(viewLifecycleOwner.lifecycleScope)
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
		viewModel.cartStateSharedFlow.onEach(::handleUiState)
			.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun handleUiState(state: CartState) {
		when (state) {
			is CartState.Success -> {
				toggleState(false)
				adapter.submitList(state.products)
				binding.allAmount.text = getString(R.string.total_price, state.totalPrice)
			}
			CartState.Empty -> {
				toggleState(true)
				adapter.submitList(null)
			}
			is CartState.Error -> {
				TODO()
			}
			CartState.Init -> {

			}
		}
	}

	private fun toggleState(isEmpty: Boolean){

		val animation = MaterialFade().apply {
			duration = 200L
		}
		binding.apply {
			TransitionManager.beginDelayedTransition(root, animation)
			goToProductsButton.isVisible = isEmpty
			footerCardView.isVisible = !isEmpty
			emptyCartLotttieView.isVisible = isEmpty
			if (isEmpty){
				emptyCartLotttieView.playAnimation()
			} else {
				emptyCartLotttieView.cancelAnimation()
			}
			cartRecyclerView.doOnPreDraw {
				it.isVisible = !isEmpty
			}
		}
	}

	private fun initList() {
		with(binding.cartRecyclerView) {
			adapter = this@CartFragment.adapter
		}
		initSwipeToDelete()
	}

	private fun initSwipeToDelete() {
		val swipeToDeleteCallback = SwipeToDelete(::onItemSwipedToDelete, requireContext())
		ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.cartRecyclerView)
	}

	private fun onItemSwipedToDelete(positionForRemove: Int){
		val guid = adapter.getGuidByPosition(positionForRemove)
		updateCart(guid, 0)
		showRestoreSnackBar(guid)
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