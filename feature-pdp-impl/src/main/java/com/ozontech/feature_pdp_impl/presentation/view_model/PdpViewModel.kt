package com.ozontech.feature_pdp_impl.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozontech.feature_pdp_impl.domain.interactors.PdpInteractor
import com.ozontech.feature_pdp_impl.presentation.mappers.toVO
import com.ozontech.feature_pdp_impl.presentation.view_objects.ProductVO
import javax.inject.Inject

class PdpViewModel @Inject constructor(
    private val interactor: PdpInteractor,
) : ViewModel() {

    private val _productMutableLiveData = MutableLiveData<ProductVO>()
    val productLiveData: LiveData<ProductVO> = _productMutableLiveData


     fun getProductByGuid(guid: String) {
        val product = interactor.getProductByGuid(guid)
        product?.let { productDO ->
            _productMutableLiveData.postValue(productDO.toVO())
        }
    }
}

