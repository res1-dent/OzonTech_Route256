package com.ozontech.feature_products_impl.presentation.view_model

import androidx.lifecycle.*
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.presentation.mappers.toVO
import com.ozontech.feature_products_impl.presentation.view_objects.UiState
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
	private val interactor: ProductListInteractor,
) : ViewModel() {


	private val uiStateMutableLiveData = MutableLiveData<UiState>(UiState.Loading)
	val uiStateLiveData: LiveData<UiState> = uiStateMutableLiveData

	fun getProducts() {
		uiStateMutableLiveData.postValue(UiState.Loading)
		interactor.getProducts().map { it.toVO() }.let {
			if (it.isEmpty()){
				uiStateMutableLiveData.postValue(UiState.Error)
			} else {
				uiStateMutableLiveData.postValue(UiState.Success(it))
			}
		}
	}

	fun incrementCounter(guid: String) {
		interactor.incrementCounter(guid)
	}
}