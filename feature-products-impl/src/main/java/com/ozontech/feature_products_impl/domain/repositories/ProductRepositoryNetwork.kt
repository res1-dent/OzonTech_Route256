package com.ozontech.feature_products_impl.domain.repositories

import androidx.lifecycle.LiveData
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO

interface ProductRepositoryNetwork {

	fun fetchListOfProducts(): LiveData<List<ProductInListDO>>

	fun incrementCounter(guid: String)
}