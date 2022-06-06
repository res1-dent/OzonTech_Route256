package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.homework2.R
import com.ozontech.homework2.databinding.FragmentProductListBinding
import com.ozontech.homework2.presentation.adapter.ProductsAdapter
import com.ozontech.homework2.presentation.adapter.decorators.ProductItemDecorator
import com.ozontech.homework2.presentation.viewModel.ProductListViewModel
import com.ozontech.homework2.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private val binding by viewBinding(FragmentProductListBinding::bind)
    private val viewModel: ProductListViewModel by viewModels()
    private val adapter: ProductsAdapter by autoCleared { ProductsAdapter(::navigateToDetails) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        setListeners()
    }

    private fun setListeners() {
        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addFragment)
        }
    }

    private fun observeViewModelState() {
        viewModel.productsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initList() {
        binding.productList.adapter = this@ProductListFragment.adapter
        binding.productList.addItemDecoration(ProductItemDecorator(20))
    }

    private fun navigateToDetails(guid: String) {
        viewModel.incrementCounter(guid)
        val action = ProductListFragmentDirections.actionProductListFragmentToPDPFragment(guid)
        findNavController().navigate(action)
    }


}