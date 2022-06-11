package com.ozontech.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.presentation.mappers.toVO
import com.ozontech.feature_products_impl.presentation.view_objects.ProductInListVO
import javax.inject.Inject


class ProductListViewModel @Inject constructor(
    private val interactor: ProductListInteractor,
) : ViewModel() {

    private val _productsListMutableLiveData = MutableLiveData<List<ProductInListVO>>()
    val productsListLiveData: LiveData<List<ProductInListVO>> = _productsListMutableLiveData

    fun getListOfProducts() {
        val productList = interactor.getProducts().map { it.toVO()}
        _productsListMutableLiveData.postValue(productList)
    }

    fun incrementCounter(guid: String) {
        interactor.incrementCounter(guid)
    }
}
