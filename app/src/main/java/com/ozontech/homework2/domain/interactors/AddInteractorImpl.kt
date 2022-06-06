package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.repositories.AddRepository

class AddInteractorImpl (
    private val repository:AddRepository
): AddInteractor {
    override  fun addRandomProduct() {
        repository.addRandomProduct()
    }
}