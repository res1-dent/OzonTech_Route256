package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.db.ProductDao
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.data.mappers.toProducts
import com.ozontech.homework2.data.storage.CounterPreference
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {


    override suspend fun fetchProductDetails(guid: String): ProductDO {
        return productDao.getProductByGuid(guid).toProducts().toDO()
    }
}