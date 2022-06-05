package com.ozontech.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.homework2.domain.interactors.ProductListInteractor
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    interactor: ProductListInteractor
) : ViewModel() {

    private val _productsList = MutableLiveData<List<ProductInListVO>>()
    val productsList: LiveData<List<ProductInListVO>> = _productsList

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        try {
            _productsList.postValue(interactor.getProducts())
        } catch (e: Exception) {
            _isError.postValue(true)
        }
    }

}