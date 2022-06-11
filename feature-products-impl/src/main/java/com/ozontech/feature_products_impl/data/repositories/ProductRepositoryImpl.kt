package com.ozontech.feature_products_impl.data.repositories

import com.ozontech.core_network_api.ProductsApi
import com.ozontech.feature_products_impl.data.mappers.toDO
import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO
import com.ozontech.feature_products_impl.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductRepository {

    override fun fetchListOfProducts(): List<ProductInListDO> =
        productsApi.getProducts().map { it.toDO() }

    override fun incrementCounter(guid: String) {
         productsApi.incrementCounter(guid)
    }
}