package com.ozontech.homework2.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ozontech.homework2.databinding.FragmentAddBinding
import com.ozontech.homework2.di.ServiceLocator
import com.ozontech.homework2.presentation.viewModel.AddViewModel
import com.ozontech.homework2.presentation.viewModel.viewModelCreator
import com.ozontech.homework2.utils.inflate

class AddFragment : Fragment() {

    private val binding by viewBinding(FragmentAddBinding::bind)
    private val viewModel: AddViewModel by viewModelCreator {
        AddViewModel(ServiceLocator.addInteractor)
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
        return container?.inflate(FragmentAddBinding::inflate)?.root
    }
}