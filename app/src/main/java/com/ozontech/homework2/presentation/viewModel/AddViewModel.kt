package com.ozontech.homework2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.homework2.domain.interactors.AddInteractor
import kotlinx.coroutines.launch

class AddViewModel (
    private val interactor: AddInteractor
) : ViewModel() {

    fun addRandom() {
        viewModelScope.launch {
            interactor.addRandomProduct()
        }
    }
}