package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.homework2.R
import com.ozontech.homework2.databinding.FragmentProductListBinding
import com.ozontech.homework2.di.ServiceLocator
import com.ozontech.homework2.presentation.adapter.ProductsAdapter
import com.ozontech.homework2.presentation.adapter.decorators.ProductItemDecorator
import com.ozontech.homework2.presentation.viewModel.ProductListViewModel
import com.ozontech.homework2.presentation.viewModel.viewModelCreator
import com.ozontech.homework2.utils.autoCleared
import com.ozontech.homework2.utils.inflate


class ProductListFragment : Fragment() {

    private val binding by viewBinding(FragmentProductListBinding::bind)
    private val viewModel: ProductListViewModel by viewModelCreator {
        ProductListViewModel(ServiceLocator.productInListInteractor)
    }
    private val adapter: ProductsAdapter by autoCleared { ProductsAdapter(::navigateToPDPFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
        setListeners()
    }

    private fun setListeners() {
        binding.addFab.setOnClickListener(::navigateToAddFragment)
    }

    private fun navigateToAddFragment(v: View) {
        findNavController().navigate(R.id.action_productListFragment_to_addFragment)
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
            productListRecycler.addItemDecoration(ProductItemDecorator(20))
        }
    }

    private fun navigateToPDPFragment(guid: String) {
        viewModel.incrementCounter(guid)
        val action = ProductListFragmentDirections.actionProductListFragmentToPDPFragment(guid)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(FragmentProductListBinding::inflate)?.root
    }
}