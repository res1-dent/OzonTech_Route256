package com.ozontech.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.BaseFragment
import com.ozontech.core_utils.autoCleared
import com.ozontech.core_utils.inflate
import com.ozontech.feature_products_api.ProductNavigationApi
import com.ozontech.feature_products_impl.databinding.FragmentProductListBinding
import com.ozontech.feature_products_impl.di.FeatureProductComponent
import com.ozontech.feature_products_impl.presentation.adapter.ProductsAdapter
import com.ozontech.feature_products_impl.presentation.adapter.decorators.ProductItemDecorator
import com.ozontech.feature_products_impl.presentation.view_model.ProductListViewModel
import javax.inject.Inject


class ProductListFragment :
    BaseFragment<FeatureProductComponent>(component = FeatureProductComponent::class) {

    private val binding by viewBinding(FragmentProductListBinding::bind)

    private val viewModel: ProductListViewModel by viewModels {
        currentComponent.getFabric()
    }
    private val adapter: ProductsAdapter by autoCleared {
        ProductsAdapter(::onProductClick)
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
    }



    private fun setListeners() {
        binding.addFab.setOnClickListener() {
            productNavigationApi.navigateToAdd(this)
        }
    }

    private fun observeViewModelState() {
        viewModel.getProducts().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, ::toggleLoadingState)
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
        productNavigationApi.navigateToPDP(this, guid)
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        with(binding) {
            productListRecycler.isVisible = isLoading.not()
            progress.isVisible = isLoading
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