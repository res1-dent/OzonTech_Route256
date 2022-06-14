package com.ozontech.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.core_utils.*
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
        currentComponent.fabric()
    }
    private val adapter: ProductsAdapter by autoCleared {
        ProductsAdapter(
            ::navigateToPDPFragment
        )
    }

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    override fun onAttach(context: Context) {
        super.onAttach(context)
        currentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        setListeners()
    }

    private fun setListeners() {
        binding.addFab.setOnClickListener(::navigateToAddFragment)
    }

    private fun observeViewModelState() {
        viewModel.getListOfProducts()
        viewModel.productsListLiveData.observe(viewLifecycleOwner) {
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

    private fun navigateToAddFragment(v: View) {
        productNavigationApi.navigateToAdd(this)
    }

    private fun navigateToPDPFragment(guid: String) {
        viewModel.incrementCounter(guid)
        productNavigationApi.navigateToPDP(this, guid)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(FragmentProductListBinding::inflate)?.root
    }
}