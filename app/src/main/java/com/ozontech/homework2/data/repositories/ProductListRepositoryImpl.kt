package com.ozontech.homework2.data.repositories

import android.app.Application
import com.ozontech.homework2.data.db.ProductInListDao
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.data.mappers.toProductInList
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val productsDao: ProductInListDao
) : ProductListRepository {


    override suspend fun fetchListOfProducts(): Flow<List<ProductInListDO>> = flow {
        productsDao.getAllProducts()
            .collect {
                emit(it.map { productsInListDB -> productsInListDB.toProductInList().toDO() })
            }
    }
}