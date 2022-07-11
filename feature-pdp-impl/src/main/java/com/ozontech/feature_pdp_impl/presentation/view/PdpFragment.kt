package com.ozontech.feature_pdp_impl.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.*
import com.ozontech.core_utils.adapters.ImagesAdapter
import com.ozontech.feature_pdp_impl.databinding.FragmentPdpBinding
import com.ozontech.feature_pdp_impl.di.FeaturePdpComponent
import com.ozontech.feature_pdp_impl.presentation.view_model.PdpViewModel
import com.ozontech.feature_pdp_impl.presentation.view_objects.PdpUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.recyclerview.scrollEvents
import reactivecircus.flowbinding.recyclerview.scrollStateChanges

class PdpFragment : BaseFragment<FeaturePdpComponent>(component = FeaturePdpComponent::class) {


	private val viewModel: PdpViewModel by viewModels {
		currentComponent.getFabric()
	}

	private val binding by viewBinding(FragmentPdpBinding::bind)
	private val guid: String by stringArgs(KEY_GUID)
	private val adapter: ImagesAdapter by autoCleared { ImagesAdapter() }



	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		observeViewModelState()
		initList()
		viewModel.getProductByGuid(guid)
		setListeners()
	}

	private fun setListeners() {
		binding.cartView.productsSharedFlow.onEach {
			viewModel.updateCart(guid, it)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun initList() {
		with(binding.imagesRecycler) {
			adapter = this@PdpFragment.adapter
			PagerSnapHelper().attachToRecyclerView(this)
			scrollStateChanges().onEach {
				if (it == RecyclerView.SCROLL_STATE_IDLE){
					val itemPosition =
						(this.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
					binding.tabLayout.getTabAt(itemPosition)?.select()
				}
			}.launchIn(viewLifecycleOwner.lifecycleScope)
		}
	}

	private fun initTabLayout(size: Int) {
		if (binding.tabLayout.tabCount == size) return
		binding.tabLayout.removeAllTabs()
		(1..size).map {
			binding.tabLayout.apply {
				addTab(newTab().apply {
					icon = ContextCompat.getDrawable(requireContext(), com.ozontech.feature_pdp_impl.R.drawable.circle)
					view.isClickable = false
				})
			}
		}
	}

	private fun observeViewModelState() {
		viewModel.productStateFlow.onEach(::handleUiState)
			.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun handleUiState(state: PdpUiState) {
		when (state) {
			is PdpUiState.Success -> {
				val product = state.product
				with(binding) {
					initTabLayout(product.images.size)
					binding.cartView.updateRestOfProducts(state.product.availableCount)
					adapter.submitList(state.product.images)
					nameTextView.text = product.name
					priceTextView.text = getString(R.string.price, product.price)
					ratingRatingBar.rating = product.rating
					descriptionTextView.text = product.description
					availableCountTextView.text =
						getString(R.string.available_count, product.availableCount)
					countTextView.text =
						getString(R.string.count_string, product.count)
				}
			}
			is PdpUiState.Error -> {

			}
			is PdpUiState.Loading -> {

			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return container?.inflate(FragmentPdpBinding::inflate)?.root
	}

	companion object {
		private const val KEY_GUID = "guid"
		fun newInstance(guid: String) =
			PdpFragment().apply {
				arguments = Bundle().apply {
					putString(KEY_GUID, guid)
				}
			}
	}
}


