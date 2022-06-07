package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.domainObjects.ProductDO

interface PDPInteractor {
    fun getProductByGuid(guid:String): ProductDO?
}