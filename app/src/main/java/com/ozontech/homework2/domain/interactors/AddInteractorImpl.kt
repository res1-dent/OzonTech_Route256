package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.repositories.AddRepository
import javax.inject.Inject

class AddInteractorImpl @Inject constructor(
    private val repository:AddRepository
): AddInteractor {
    override suspend fun addRandomProduct() {
        repository.addRandomProduct()
    }
}