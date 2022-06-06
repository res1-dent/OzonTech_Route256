package com.ozontech.homework2.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozontech.homework2.domain.interactors.ProductListInteractor
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProductListViewModel(
    private val interactor: ProductListInteractor,
) : ViewModel() {

    private val _productsList = MutableLiveData<List<ProductInListVO>>()
    val productsList: LiveData<List<ProductInListVO>> = _productsList

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListOfProducts() {
        viewModelScope.launch {
            interactor.getProducts().collect {
                Log.e("ProductListViewModel", "list = $it")
                _productsList.postValue(it)
            }
        }
    }

    fun incrementCounter(guid: String) {
        viewModelScope.launch {
            interactor.incrementCounter(guid)
        }
    }
}