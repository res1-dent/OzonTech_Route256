package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ozontech.homework2.R
import com.ozontech.homework2.databinding.PdpFragmentBinding
import com.ozontech.homework2.presentation.viewModel.PDPViewModel
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PDPFragment : Fragment(R.layout.pdp_fragment) {

    private val binding by viewBinding(PdpFragmentBinding::bind)
    private val args: PDPFragmentArgs by navArgs()
    private val viewModel: PDPViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.guid.let {
            viewModel.getProductByGuid(it)
        }
        observeViewModelState()
    }

    private fun observeViewModelState() {
        viewModel.productLD.observe(viewLifecycleOwner) {
            updateProduct(it)
            /* if (!it.isInCart) {
                 showMessage(it.counter)
             }*/
        }
    }

    /*  private fun showMessage(counter: Int) {
          Log.e("counter", "counter = $counter")
          if (counter > 1) {
              Log.e("counter", "counter in if = $counter")
              val message = getString(R.string.counter_message, counter)
              Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
          }
      }*/

    private fun updateProduct(product: ProductVO) {
        Glide.with(requireView()).load(product.images.first()).into(binding.productIV)
        with(binding) {
            nameTV.text = product.name
            priceTV.text = getString(R.string.price, product.price)
            rating.rating = product.rating
            description.text = product.description
            avCount.text = getString(R.string.available_count, product.availableCount ?: 0)
            count.text =
                getString(R.string.count_string, product.count ?: 0)
        }

    }
}