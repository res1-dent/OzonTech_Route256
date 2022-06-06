package com.ozontech.homework2.di


import com.ozontech.homework2.data.repositories.AddRepositoryImpl
import com.ozontech.homework2.data.repositories.MockRepository
import com.ozontech.homework2.data.repositories.ProductListRepositoryImpl
import com.ozontech.homework2.data.repositories.ProductRepositoryImpl
import com.ozontech.homework2.domain.interactors.*
import com.ozontech.homework2.domain.repositories.AddRepository
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.domain.repositories.ProductRepository

// Подробнее можете почитать [тут](http://sergeyteplyakov.blogspot.com/2013/03/di-service-locator.html),
// [тут](https://habr.com/ru/post/465395/) и [тут](https://javatutor.net/articles/j2ee-pattern-service-locator).

object ServiceLocator {

    private val mockRepository: MockRepository by lazy {
        MockRepository()
    }

    private val productListRepository: ProductListRepository by lazy {
        ProductListRepositoryImpl(mockRepository)
    }

    private val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(mockRepository)
    }

    private val addRepository: AddRepository by lazy {
        AddRepositoryImpl(mockRepository)
    }

    val addInteractor: AddInteractor by lazy {
        AddInteractorImpl(addRepository)
    }

    val pdpInteractor: PDPInteractor by lazy {
        PDPInteractorImpl(productRepository)
    }

    val productInListInteractor: ProductListInteractor by lazy {
        ProductListInteractorImpl(productListRepository)
    }

}
