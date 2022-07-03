package com.ozontech.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_api.ProductNavigationApi
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
import javax.inject.Inject

class ProductListFragment :
	BaseFragment<FeatureProductComponent>(component = FeatureProductComponent::class) {

	private val binding by viewBinding(FragmentProductListBinding::bind)

	private val viewModel: ProductListViewModel by viewModels {
		currentComponent.getFabric()
	}
	internal val adapter: ProductsAdapter by autoCleared {
		ProductsAdapter(::onProductClick, ::onCartAddClick, lifecycleScope)
	}

	@Inject
	lateinit var productNavigationApi: ProductNavigationApi

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
			productNavigationApi.navigateToAdd(this)
		}.launchIn(lifecycleScope)
	}

	private fun observeViewModelState() {
		viewModel.uiStateStateFlow.onEach(::handleUiState)
			.launchIn(viewLifecycleOwner.lifecycleScope)
		viewModel.workManager.distinctUntilChanged().onEach { workInfo ->
			workInfo.firstOrNull()?.let(viewModel::handleWorkInfo)
		}.launchIn(lifecycleScope)
	}

	private fun initList() {
		val gridLayoutManager = GridLayoutManager(requireContext(), 2).apply {
			spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
				override fun getSpanSize(position: Int): Int {
					return when (adapter.currentList.getOrNull(position)) {
						is ProductInListRecyclerViewModel.ProductInListVO -> 1
						is ProductInListRecyclerViewModel.Header -> 2
						else -> -1
					}
				}
			}
		}
		with(binding.productListRecycler) {
			adapter = this@ProductListFragment.adapter
			addItemDecoration(
				ProductItemDecorator(
					20
				)
			)
			layoutManager = gridLayoutManager
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
			}
			is UiState.Loading -> {
				toggleLoadingState(true)
			}
		}
	}

	private fun onProductClick(guid: String) {
		viewModel.incrementCounter(guid)
		productNavigationApi.navigateToPDP(this, guid)
	}

	private fun onCartAddClick(guid: String, isInCart: Boolean) {
		viewModel.toggleCart(guid, isInCart)
	}

	private fun toggleLoadingState(isLoading: Boolean) {
		with(binding) {
			progress.isVisible = isLoading
		}
	}

	private fun updateInfo() {
		viewLifecycleOwner.lifecycleScope.launch {
			while (isActive) {
				delay(300_000)
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



