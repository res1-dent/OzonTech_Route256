package com.ozontech.feature_products_impl.domain.interactors

import com.ozontech.feature_products_impl.domain.domain_objects.ProductInListDO

interface ProductListInteractor {

     fun getProducts(): List<ProductInListDO>

     fun incrementCounter(guid: String)
}