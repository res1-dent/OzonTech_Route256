package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.presentation.viewObjects.ProductVO

interface PDPInteractor {
    fun getProductByGuid(guid:String): ProductVO?
}