package com.ozontech.feature_products_impl.domain.interactors

import android.util.Log
import com.ozontech.feature_products_impl.R
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.mappers.toVO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import com.ozontech.feature_products_impl.domain.view_objects.ProductInListRecyclerViewModel
import com.ozontech.feature_products_impl.domain.view_objects.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class ProductListInteractorImpl @Inject constructor(
	private val repository: ProductRepository
) : ProductListInteractor {

	override suspend fun getProducts(): Flow<UiState> =
		repository.fetchListOfProducts().combine(repository.getProductsInCartCount()){ list: List<ProductInListDO>, i: Int ->
			if (list.isNotEmpty()){
				val newList = addHeadersToProductsList(list.map { it.toVO() })
				UiState.Success(newList, i)
			} else UiState.Error
		}

	override suspend fun incrementCounter(guid: String) {
		repository.incrementCounter(guid)
	}

	override suspend fun updateInfo() {
		repository.updateInfo()
	}

	override suspend fun toggleCart(guid: String) {
		repository.toggleCart(guid)
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