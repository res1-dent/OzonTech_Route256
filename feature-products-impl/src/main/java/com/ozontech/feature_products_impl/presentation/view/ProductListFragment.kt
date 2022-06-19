package com.ozontech.feature_products_impl.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_impl.databinding.FragmentProductListBinding
import com.ozontech.feature_products_impl.di.FeatureProductComponent
import com.ozontech.feature_products_impl.presentation.adapter.ProductsAdapter
import com.ozontech.feature_products_impl.presentation.adapter.decorators.ProductItemDecorator
import com.ozontech.feature_products_impl.presentation.view_model.ProductListViewModel


class ProductListFragment :
    BaseFragment<FeatureProductComponent>(component = FeatureProductComponent::class) {

    private val binding by viewBinding(FragmentProductListBinding::bind)

    private val viewModel: ProductListViewModel by viewModels {
        currentComponent.fabric()
    }
    private val adapter: ProductsAdapter by autoCleared {
        ProductsAdapter(::onProductClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Worker", "OncreateFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        setListeners()
    }


    private fun setListeners() {
        binding.addFab.setOnClickListener() {
            viewModel.navigateToAddFragment()
        }
    }

    private fun observeViewModelState() {
        viewModel.productsListLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun initList() {
        with(binding) {
            productListRecycler.adapter = this@ProductListFragment.adapter
            productListRecycler.addItemDecoration(
                ProductItemDecorator(
                    20
                )
            )
        }
    }

    private fun onProductClick(guid: String) {
        viewModel.navigateToPDPFragment(guid)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(FragmentProductListBinding::inflate)?.root
    }
}