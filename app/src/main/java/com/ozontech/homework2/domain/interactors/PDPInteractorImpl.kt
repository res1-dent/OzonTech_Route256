package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.mappers.toVO
import com.ozontech.homework2.domain.repositories.ProductRepository
import com.ozontech.homework2.presentation.viewObjects.ProductVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(
    private val repository: ProductRepository
) : PDPInteractor {

    override suspend fun getProductByGuid(guid: String): ProductVO = withContext(Dispatchers.IO) {
        repository.fetchProductDetails(guid).toVO()
    }


}