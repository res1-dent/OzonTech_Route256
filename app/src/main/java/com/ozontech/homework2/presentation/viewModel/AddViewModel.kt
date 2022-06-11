package com.ozontech.homework2.presentation.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.homework2.domain.interactors.AddInteractor
import kotlinx.coroutines.launch

class AddViewModel(
    private val interactor: AddInteractor
) : ViewModel() {

    fun addRandomProduct(v : View) {
        viewModelScope.launch {
            interactor.addRandomProduct()
        }
    }
}