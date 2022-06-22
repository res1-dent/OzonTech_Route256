package com.ozontech.feature_products_impl.domain.interactors

import androidx.lifecycle.LiveData
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO

interface ProductListInteractor {

	fun getProducts(): LiveData<List<ProductInListDO>>

	fun incrementCounter(guid: String)
}