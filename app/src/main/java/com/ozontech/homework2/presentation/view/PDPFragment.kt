package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ozontech.homework2.R
import com.ozontech.homework2.databinding.PdpFragmentBinding
import com.ozontech.homework2.di.ServiceLocator
import com.ozontech.homework2.presentation.viewModel.PDPViewModel
import com.ozontech.homework2.presentation.viewModel.viewModelCreator
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import com.ozontech.homework2.utils.inflate

class PDPFragment : Fragment() {

    private val binding by viewBinding(PdpFragmentBinding::bind)
    private val args: PDPFragmentArgs by navArgs()


    private val viewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(ServiceLocator.pdpInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModelState()
    }

    private fun init() {
        //With Nav Component
       // viewModel.getProductByGuid(args.guid)

        //Default
        requireArguments().getString(KEY_GUID)?.let {
            viewModel.getProductByGuid(it)
        }
    }

    private fun observeViewModelState() {
        viewModel.productLiveData.observe(viewLifecycleOwner, ::updateProduct)
    }


    private fun updateProduct(product: ProductVO) {
        with(binding) {
            Glide.with(requireView()).load(product.images.first()).into(productImageView)
            nameTextView.text = product.name
            priceTextView.text = getString(R.string.price, product.price)
            ratingRatingBar.rating = product.rating
            descriptionTextView.text = product.description
            availableCountTextView.text =
                getString(R.string.available_count, product.availableCount ?: 0)
            countTextView.text =
                getString(R.string.count_string, product.count ?: 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(PdpFragmentBinding::inflate)?.root
    }

    companion object {
        private const val KEY_GUID = "guid"

        fun newInstance(guid: String): PDPFragment {
            val bundle = Bundle().apply { putString(KEY_GUID, guid) }
            return PDPFragment().apply {
                arguments = bundle
            }
        }
    }
}