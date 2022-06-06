package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductRepository
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PDPInteractorImpl (
    private val repository: ProductRepository
) : PDPInteractor {

    override  fun getProductByGuid(guid: String): ProductVO? =
        repository.fetchProductDetails(guid)?.toVO()



}