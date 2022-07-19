package com.ozontech.feature_cart_impl.data.repositories

import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.feature_cart_impl.data.mappers.toProduct
import com.ozontech.feature_cart_impl.domain.repositories.CartRepository
import com.ozontech.feature_cart_impl.domain.view_object.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
	private val database: ProductsDatabase
) : CartRepository {

	override val footerStateFlow: MutableSharedFlow<Int> = MutableSharedFlow(extraBufferCapacity = 1, replay = 1)

	override suspend fun getProductsInCart(): Flow<List<Product>> {
		return database.productsInCart.map { list ->
			footerStateFlow.emit(list.sumOf { it.price.toInt() * it.amount })
			list.map { item ->
				item.toProduct()
			}
		}
	}

	override suspend fun updateCart(guid: String, count: Int) {
		database.addProductToCart(guid, count)
	}
}