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
) : ViewModel() {



    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = isLoadingMutableLiveData

    init {
        isLoadingMutableLiveData.postValue(true)
    }

    fun getProducts(): LiveData<List<ProductInListVO>> {
     return Transformations.map(interactor.getProducts()) {
           if (it.isNotEmpty())
           isLoadingMutableLiveData.postValue(false)
           it.let {
               it.map { productInListDO -> productInListDO.toVO() }
           }
       }
    }

    fun incrementCounter(guid: String){
        interactor.incrementCounter(guid)
    }
}