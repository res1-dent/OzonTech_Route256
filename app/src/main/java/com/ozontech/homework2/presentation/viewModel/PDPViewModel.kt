package com.ozontech.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.homework2.domain.interactors.PDPInteractor
import com.ozontech.homework2.presentation.mappers.toVO
import com.ozontech.homework2.presentation.viewObjects.ProductVO

class PDPViewModel(
    private val interactor: PDPInteractor
) : ViewModel() {

    private val _productMutableLiveData = MutableLiveData<ProductVO>()
    val productLiveData: LiveData<ProductVO> = _productMutableLiveData

    fun getProductByGuid(guid: String) {
        val product = interactor.getProductByGuid(guid)
        product?.let { productDO->
            _productMutableLiveData.postValue(productDO.toVO())
        }
    }
}