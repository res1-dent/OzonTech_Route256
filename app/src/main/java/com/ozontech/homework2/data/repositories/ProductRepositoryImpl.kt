package com.ozontech.homework2.data.repositories

import android.app.Application
import com.google.gson.GsonBuilder
import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.data.storage.CounterPreference
import com.ozontech.homework2.domain.domainObjects.ProductDO
import com.ozontech.homework2.domain.repositories.ProductRepository
import com.ozontech.homework2.utils.getJsonDataFromAsset
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val context: Application,
    private val dataStore: CounterPreference
) : ProductRepository {


    override fun fetchProductDetails(guid: String): ProductDO {
        getJsonDataFromAsset(context, PRODUCT_LIST_JSON)?.let { data ->
            val gson = GsonBuilder().create()
            return gson.fromJson(data, Array<Product>::class.java).map { it.toDO() }
                .find { it.guid == guid } ?: throw Exception("No such product")
        } ?: throw Exception("Error fetching products")
    }

    override suspend fun getCounter(guid: String): Int {
        return dataStore.getCounter(guid)
    }

    override suspend fun incrementCounter(guid: String) {
        dataStore.incrementCounter(guid)
    }


    private companion object {
        const val PRODUCT_LIST_JSON = "Product.json"
    }


}