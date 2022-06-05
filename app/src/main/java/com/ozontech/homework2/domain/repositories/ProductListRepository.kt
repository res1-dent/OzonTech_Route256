package com.ozontech.homework2.domain.repositories

import com.ozontech.homework2.domain.domainObjects.ProductInListDO

interface  ProductListRepository {
    fun fetchListOfProducts():List<ProductInListDO>
}