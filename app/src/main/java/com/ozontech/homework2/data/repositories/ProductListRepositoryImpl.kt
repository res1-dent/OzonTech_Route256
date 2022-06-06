package com.ozontech.homework2.data.repositories

import android.os.Build
import android.text.TextUtils.indexOf
import androidx.annotation.RequiresApi
import com.ozontech.homework2.data.mappers.toDO
import com.ozontech.homework2.domain.domainObjects.ProductInListDO
import com.ozontech.homework2.domain.repositories.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListRepositoryImpl(
    private val mock: MockRepository
    ) : ProductListRepository {


    override  fun fetchListOfProducts(): Flow<List<ProductInListDO>> = flow {
        emit(mock.productInListDTOs.map { it.toDO() })
    }

    override fun incrementCounter(guid: String) {
            mock.productInListDTOs.replaceAll {
                if (it.guid == guid)
                    it.copy(counter = it.counter+1)
                else it
            }
        }
    }