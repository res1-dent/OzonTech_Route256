package com.ozontech.feature_products_impl.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ozontech.core_network_api.Key
import com.ozontech.core_utils.StringResWrapper
import com.ozontech.feature_products_api.ProductNavigationApi
import com.ozontech.feature_products_impl.R
import com.ozontech.feature_products_impl.domain.interactors.ProductListInteractor
import com.ozontech.feature_products_impl.domain.view_objects.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
	context: Application,
	private val interactor: ProductListInteractor,
	private val navigationApi: ProductNavigationApi
) : ViewModel() {

	val workManager: Flow<MutableList<WorkInfo>> = WorkManager.getInstance(context)
		.getWorkInfosByTagLiveData(Key.TAG_PRODUCT_IN_LIST_REQUEST).asFlow()

	private val uiStateMutableStateFlow = MutableStateFlow<UiState>(UiState.Loading)
	val uiStateStateFlow = uiStateMutableStateFlow.asStateFlow()

	private val showUpdateMessageMutableSharedFlow = MutableSharedFlow<StringResWrapper>()

	val showUpdateMessageSharedFlow = showUpdateMessageMutableSharedFlow.asSharedFlow()

	private fun getProducts() {
		viewModelScope.launch {
			uiStateMutableStateFlow.emit(UiState.Loading)
			interactor.getProducts().distinctUntilChanged().collect {
				uiStateMutableStateFlow.emit(it)
			}
		}
	}

	fun handleWorkInfo(workInfo: WorkInfo) {
		viewModelScope.launch {
			if (workInfo.state.isFinished) getProducts()
			when (workInfo.state) {
				WorkInfo.State.SUCCEEDED -> {
					showUpdateMessageMutableSharedFlow.emit(StringResWrapper(R.string.worker_sucess))
				}
				WorkInfo.State.FAILED -> {
					showUpdateMessageMutableSharedFlow.emit(StringResWrapper(R.string.worker_failed))
				}
			}
		}
	}

	fun incrementCounter(guid: String) {
		viewModelScope.launch {
			interactor.incrementCounter(guid)
		}
	}

	fun toggleCart(guid: String, isInCart: Boolean) {
		viewModelScope.launch {
			delay(500)
			interactor.toggleCart(guid, isInCart)
		}
	}

	fun onProductClick(guid: String) {
		incrementCounter(guid)
		navigationApi.navigateToPDP(guid)
	}

	fun onAddFabClick() {
		navigationApi.navigateToAdd()
	}

	suspend fun updateInfo() {
		interactor.updateInfo()
	}

}