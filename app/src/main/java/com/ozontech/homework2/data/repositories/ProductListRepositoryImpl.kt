package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.db.ProductDao
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.data.mappers.toProductInList
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : ProductListRepository {


    override suspend fun fetchListOfProducts(): Flow<List<ProductInListDO>> = flow {
        dao.getProductInList()
            .collect {
                emit(it.map { productsInListDB -> productsInListDB.toProductInList().toDO() })
            }
    }

    override suspend fun incrementCounter(guid: String) = withContext(Dispatchers.IO) {
        val product = dao.getProductByGuid(guid)
        val counter = product.counter + 1
        dao.updateCounter(product.copy(counter = counter))
    }
}