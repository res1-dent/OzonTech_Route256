package com.ozontech.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.presentation.mappers.toVO
import com.ozontech.feature_products_impl.presentation.view_objects.UiState
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
	private val interactor: ProductListInteractor,
) : ViewModel() {

	private val stateRefreshableLiveData = RefreshableLiveData {
		Transformations.map(interactor.getProducts()) {
			it?.let {
				val list = it.map { productInListDO -> productInListDO.toVO() }
				if (list.isNotEmpty()) UiState.Success(list)
				else UiState.Error
			} ?: UiState.Loading
		}
	}

	val stateLiveData: LiveData<UiState> = stateRefreshableLiveData

	fun refreshState() {
		if (stateRefreshableLiveData.value != null)
			stateRefreshableLiveData.refresh()
	}

	fun incrementCounter(guid: String) {
		interactor.incrementCounter(guid)
	}
}

class RefreshableLiveData<T>(
	private val source: () -> LiveData<T>
) : MediatorLiveData<T>() {

	private var liveData = source()

	init {
		this.addSource(liveData, ::observer)
	}

	private fun observer(data: T) {
		value = data
	}

	fun refresh() {
		this.removeSource(liveData)
		liveData = source()
		this.addSource(liveData, ::observer)
	}
}