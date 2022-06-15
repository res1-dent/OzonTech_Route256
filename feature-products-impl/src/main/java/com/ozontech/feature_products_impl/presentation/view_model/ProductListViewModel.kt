package com.ozontech.feature_products_impl.presentation.view_model

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.feature_products_api.ProductNavigationApi
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.presentation.mappers.toVO
import com.ozontech.feature_products_impl.presentation.view_objects.ProductInListVO
import javax.inject.Inject


class ProductListViewModel @Inject constructor(
    private val interactor: ProductListInteractor,
    private val navController: ProductNavigationApi
) : ViewModel() {

    private val _productsListMutableLiveData = MutableLiveData<List<ProductInListVO>>()
    val productsListLiveData: LiveData<List<ProductInListVO>> = _productsListMutableLiveData

    fun getListOfProducts() {
        val productList = interactor.getProducts().map { it.toVO()}
        _productsListMutableLiveData.postValue(productList)
    }

     fun navigateToAddFragment() {
        navController.navigateToAdd()
    }

    fun navigateToPDPFragment(guid: String) {
        interactor.incrementCounter(guid)
        navController.navigateToPDP(guid)
    }
}
