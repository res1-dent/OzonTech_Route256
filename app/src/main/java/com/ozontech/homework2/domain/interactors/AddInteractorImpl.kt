package com.ozontech.homework2.domain.interactors

import com.ozontech.homework2.domain.repositories.ProductRepository

class AddInteractorImpl (
    private val repository: ProductRepository
): AddInteractor {
    override  fun addRandomProduct() {
        repository.addRandomProduct()
    }
}