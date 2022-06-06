package com.ozontech.homework2.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.homework2.domain.interactors.PDPInteractor
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PDPViewModel @Inject constructor(
    private val interactor: PDPInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<ProductVO>()
    val productLD: LiveData<ProductVO> = _productLD

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter


    fun getProductByGuid(guid: String) {
        viewModelScope.launch {
            _isError.postValue(false)
            try {
                val product = interactor.getProductByGuid(guid)
                _productLD.postValue(product)
            } catch (e: Exception) {
                _isError.postValue(true)
            }
        }
    }
}