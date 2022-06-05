package com.ozontech.homework2.data.repositories

import android.app.Application
import com.google.gson.GsonBuilder
import com.ozontech.homework2.data.dto.ProductInList
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import com.ozontech.homework2.utils.getJsonDataFromAsset
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val context: Application
) : ProductListRepository {


    override fun fetchListOfProducts(): List<ProductInListDO> {
        getJsonDataFromAsset(context, PRODUCT_LIST_JSON)?.let { data ->
            val gson = GsonBuilder().create()
            return gson.fromJson(data, Array<ProductInList>::class.java).map { it.toDO() }
        } ?: throw Exception("Error fetching products")
    }

    private companion object {
        const val PRODUCT_LIST_JSON = "ProductInList.json"
    }


}