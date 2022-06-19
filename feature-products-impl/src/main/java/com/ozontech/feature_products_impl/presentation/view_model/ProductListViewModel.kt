package com.ozontech.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

     val productsListLiveData = Transformations.map(interactor.getProducts()) {
        it?.let {
            it.map { productInListDO -> productInListDO.toVO() }
        }
    }

     fun navigateToAddFragment() {
        navController.navigateToAdd()
    }

    fun navigateToPDPFragment(guid: String) {
        interactor.incrementCounter(guid)
        navController.navigateToPDP(guid)
    }
}
