package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.domainObjects.ProductInListDO

interface ProductListInteractor {
     fun getProducts(): List<ProductInListDO>
     fun incrementCounter(guid: String)
}