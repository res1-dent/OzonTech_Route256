package com.ozontech.homework2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.homework2.domain.interactors.AddInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val interactor: AddInteractor
) : ViewModel() {

    fun addRandom() {
        viewModelScope.launch {
            interactor.addRandomProduct()
        }
    }


}