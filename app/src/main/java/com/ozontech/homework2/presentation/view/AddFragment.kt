package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.homework2.R
import com.ozontech.homework2.databinding.FragmentAddBinding
import com.ozontech.homework2.di.ServiceLocator
import com.ozontech.homework2.presentation.viewModel.AddViewModel
import com.ozontech.homework2.presentation.viewModel.viewModelCreator

class AddFragment : Fragment(R.layout.fragment_add) {

    private val binding by viewBinding(FragmentAddBinding::bind)
    private val viewModel: AddViewModel by viewModelCreator {
        AddViewModel(ServiceLocator.addInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.addRandom.setOnClickListener {
            viewModel.addRandom()
        }
    }
}