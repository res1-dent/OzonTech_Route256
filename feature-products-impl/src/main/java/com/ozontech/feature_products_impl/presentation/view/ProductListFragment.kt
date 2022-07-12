package com.ozontech.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_impl.databinding.FragmentProductListBinding
import com.ozontech.feature_products_impl.di.FeatureProductComponent
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel
import com.ozontech.feature_products_impl.domain.view_objects.UiState
import com.ozontech.feature_products_impl.presentation.adapter.ProductsAdapter
import com.ozontech.feature_products_impl.presentation.adapter.decorators.ProductItemDecorator
import com.ozontech.feature_products_impl.presentation.view_model.ProductListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.android.view.clicks
import java.util.concurrent.TimeUnit

class ProductListFragment :
	BaseFragment<FeatureProductComponent>(component = FeatureProductComponent::class) {

	private val binding by viewBinding(FragmentProductListBinding::bind)

	private val viewModel: ProductListViewModel by viewModels {
		currentComponent.getFabric()
	}
	private val adapter: ProductsAdapter by autoCleared {
		ProductsAdapter(::onProductClick, ::onCartAddClick, lifecycleScope)
	}

	private fun onProductClick(guid: String) = viewModel.onProductClick(guid)


	override fun onAttach(context: Context) {
		currentComponent.inject(this)
		super.onAttach(context)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initList()
		observeViewModelState()
		setListeners()
		updateInfo()
	}

	private fun setListeners() {
		binding.addFab.clicks().onEach {
			viewModel.onAddFabClick()
		}.launchIn(lifecycleScope)
		binding.cartFab.clicks().onEach {
			viewModel.goToCart()
		}.launchIn(lifecycleScope)
	}

	private fun observeViewModelState() {
		viewModel.uiStateStateFlow.onEach(::handleUiState)
			.launchIn(viewLifecycleOwner.lifecycleScope)
		viewModel.workManager.distinctUntilChanged().onEach { workInfo ->
			workInfo.firstOrNull()?.let(viewModel::handleWorkInfo)
		}.launchIn(lifecycleScope)
		viewModel.showUpdateMessageSharedFlow.onEach {
			showSnackbar(it.stringId)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun showSnackbar(@StringRes message: Int) {
		Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
	}

	private fun initList() {
		/*val gridLayoutManager = GridLayoutManager(requireContext(), 2).apply {
			spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
				override fun getSpanSize(position: Int): Int {
					return when (adapter.currentList.getOrNull(position)) {
						is ProductInListRecyclerViewModel.ProductInListVO -> 1
						is ProductInListRecyclerViewModel.Header -> 2
						else -> -1
					}
				}
			}
		}*/
		with(binding.productListRecycler) {
			adapter = this@ProductListFragment.adapter
			addItemDecoration(
				ProductItemDecorator(
					20
				)
			)
		}
	}
	private fun handleUiState(state: UiState) {
		when (state) {
			is UiState.Error -> {
				toggleLoadingState(false)
			}
			is UiState.Success -> {
				toggleLoadingState(false)
				adapter.submitList(state.listOfProducts)
				binding.cartFab.isVisible = state.inCartCount > 0

			}
			is UiState.Loading -> {
				toggleLoadingState(true)
			}
		}
	}

	private fun onCartAddClick(guid: String) {
		viewModel.toggleCart(guid)
	}

	private fun toggleLoadingState(isLoading: Boolean) {
		with(binding) {
			productListRecycler.isVisible = isLoading.not()
			progress.isVisible = isLoading
		}
	}

	private fun updateInfo() {
		viewLifecycleOwner.lifecycleScope.launch {
			while (isActive) {
				delay(TimeUnit.MINUTES.toMillis(5))
				viewModel.updateInfo()
			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return container?.inflate(FragmentProductListBinding::inflate)?.root
	}
}



