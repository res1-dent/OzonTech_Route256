package com.ozontech.feature_products_impl.domain.interactors

import android.util.Log
import com.ozontech.feature_products_impl.R
import com.ozontech.feature_products_impl.domain.mappers.toVO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel
import com.ozontech.feature_products_impl.domain.view_objects.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
	private val repository: ProductRepository
) : ProductListInteractor {

	override suspend fun getProducts(): Flow<UiState> =
		repository.fetchListOfProducts().map {
			if (it.isNotEmpty()){
				val newList = it.map { it.toVO() }.let { addHeadersToProductsList(it) }
				UiState.Success(newList)
			} else UiState.Error
		}

	override suspend fun incrementCounter(guid: String) {
		repository.incrementCounter(guid)
	}

	override suspend fun updateInfo() {
		repository.updateInfo()
	}

	override suspend fun toggleCart(guid: String, isInCart: Boolean) {
		repository.toggleCart(guid, isInCart)
	}

	private fun addHeadersToProductsList(list: List<ProductInListRecyclerViewModel>)
			: List<ProductInListRecyclerViewModel> {
		return list.sortedBy { (it as ProductInListRecyclerViewModel.ProductInListVO).price }
			.toMutableList().apply {
			val tmp =
				indexOf(find { (it as ProductInListRecyclerViewModel.ProductInListVO).price > 100 })
			add(0, ProductInListRecyclerViewModel.Header(R.string.low_price_header))
			add(tmp + 1, ProductInListRecyclerViewModel.Header(R.string.high_price_header))
		}
	}

}