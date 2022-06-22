package com.ozontech.feature_add_product_impl.domain.interactor

import com.ozontech.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddInteractorImpl @Inject constructor(
	private val repository: AddProductRepository
) : AddInteractor {

	override fun addRandomProduct() {
		repository.addRandomProduct()
	}
}
