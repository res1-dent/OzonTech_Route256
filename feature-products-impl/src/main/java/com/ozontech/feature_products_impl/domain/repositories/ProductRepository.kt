package com.ozontech.feature_products_impl.domain.repositories

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO

interface  ProductRepository {

     fun fetchListOfProducts(): List<ProductInListDO>

     fun incrementCounter(guid: String)
}