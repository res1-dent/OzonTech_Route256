package com.ozontech.feature_products_impl.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ozontech.core_network_api.Key
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.presentation.mappers.toVO
import com.ozontech.feature_products_impl.presentation.view_objects.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
	context: Application,
	private val interactor: ProductListInteractor,
) : ViewModel() {

	val workManager: Flow<MutableList<WorkInfo>> = WorkManager.getInstance(context)
		.getWorkInfosByTagLiveData(Key.TAG_PRODUCT_IN_LIST_REQUEST).asFlow()

	private val uiStateMutableStateFlow = MutableStateFlow<UiState>(UiState.Loading)
	val uiStateStateFlow = uiStateMutableStateFlow.asStateFlow()

	init {
		getProducts()
	}

	private fun getProducts() {
		viewModelScope.launch {
			uiStateMutableStateFlow.emit(UiState.Loading)
			interactor.getProducts().distinctUntilChanged().collect {
				if (it.isEmpty())
					uiStateMutableStateFlow.emit(UiState.Error)
				else uiStateMutableStateFlow.emit(UiState.Success(it.map { it.toVO() }))
			}
		}
	}

	fun handleWorkInfo(workInfo: WorkInfo) {
		viewModelScope.launch {
			when (workInfo.state) {
				WorkInfo.State.SUCCEEDED -> {

				}
				WorkInfo.State.FAILED -> {

				}
				WorkInfo.State.ENQUEUED -> {
					uiStateMutableStateFlow.emit(UiState.Loading)
				}
				else -> {
					uiStateMutableStateFlow.emit(UiState.Error)
				}
			}
		}
	}

	fun incrementCounter(guid: String) {
		viewModelScope.launch {
			interactor.incrementCounter(guid)
		}
	}

	suspend fun updateInfo() {
		interactor.updateInfo()
	}

}