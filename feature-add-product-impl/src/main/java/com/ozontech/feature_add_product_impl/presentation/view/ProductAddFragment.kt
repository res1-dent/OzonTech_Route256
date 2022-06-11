package com.ozontech.feature_add_product_impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.getComp
import com.ozontech.core_utils.inflate
import com.ozontech.core_utils.releaseComp
import com.ozontech.feature_add_product_impl.databinding.FragmentProductAddBinding
import com.ozontech.feature_add_product_impl.di.FeatureProductAddComponent
import com.ozontech.feature_add_product_impl.presentation.viewModel.AddViewModel

class ProductAddFragment: Fragment() {

	private val binding by viewBinding(FragmentProductAddBinding::bind)

	private val viewModel: AddViewModel by viewModels {
		getComp(FeatureProductAddComponent::class).fabric()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setListeners()
	}

	private fun setListeners() {
		binding.addRandomButton.setOnClickListener(viewModel::addRandomProduct)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return container?.inflate(FragmentProductAddBinding::inflate)?.root
	}

	override fun onPause() {
		super.onPause()
		if (isRemoving){
			releaseComp(FeatureProductAddComponent::class)
		}
	}

}