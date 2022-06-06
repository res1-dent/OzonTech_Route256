package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.db.ProductDao
import com.ozontech.homework2.data.db.models.products.ProductDB
import com.ozontech.homework2.domain.repositories.AddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
) : AddRepository {

    override suspend fun addRandomProduct() = withContext(Dispatchers.IO) {
        val product = generateRandomProduct()
        productDao.insertProductDB(listOf(product))
    }

    private suspend fun generateRandomProduct(): ProductDB = withContext(Dispatchers.IO) {
        productDao.getAllProducts().first().random()
            .copy(guid = UUID.randomUUID().toString(), counter = 0)
    }

}