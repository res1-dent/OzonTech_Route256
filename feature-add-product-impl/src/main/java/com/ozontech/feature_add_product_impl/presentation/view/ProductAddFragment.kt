package com.ozontech.feature_add_product_impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.inflate
import com.ozontech.feature_add_product_impl.databinding.FragmentProductAddBinding
import com.ozontech.feature_add_product_impl.di.FeatureProductAddComponent
import com.ozontech.feature_add_product_impl.presentation.viewModel.AddViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

class ProductAddFragment :
	BaseFragment<FeatureProductAddComponent>(component = FeatureProductAddComponent::class) {

	private val binding by viewBinding(FragmentProductAddBinding::bind)

	private val viewModel: AddViewModel by viewModels {
		currentComponent.getFabric()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setListeners()
	}

	private fun setListeners() {
		binding.addRandomButton.clicks()
			.onEach(viewModel::addRandomProduct)
			.launchIn(viewLifecycleOwner.lifecycleScope)
	}


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return container?.inflate(FragmentProductAddBinding::inflate)?.root
	}
}