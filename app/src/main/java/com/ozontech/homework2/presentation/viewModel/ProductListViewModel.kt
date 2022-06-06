package com.ozontech.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.homework2.domain.interactors.ProductListInteractor
import com.ozontech.homework2.presentation.viewObjects.ProductInListVO


class ProductListViewModel(
    private val interactor: ProductListInteractor,
) : ViewModel() {

    private val _productsListMutableLiveData = MutableLiveData<List<ProductInListVO>>()
    val productsListLiveData: LiveData<List<ProductInListVO>> = _productsListMutableLiveData

    fun getListOfProducts() {
        val productList = interactor.getProducts()
        _productsListMutableLiveData.postValue(productList)
    }

    fun incrementCounter(guid: String) {
        interactor.incrementCounter(guid)

    }
}
