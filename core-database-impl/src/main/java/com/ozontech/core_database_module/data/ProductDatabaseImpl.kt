package com.ozontech.core_database_module.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInCartSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
import com.ozontech.core_database_module.data.mappers.toProductInCart
import com.ozontech.core_database_module.data.mappers.toProductInListDroSharedPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class ProductDatabaseImpl @Inject constructor(context: Context, private val gson: Gson) :
	ProductsDatabase {

	private val dataStore = context.DATASTORE

	override val products: Flow<List<ProductDtoSharedPrefs>>
		get() = dataStore.data.map {
			it[KEY_PRODUCT]?.let { json ->
				gson.fromJson(json, TYPE_PRODUCT)
			} ?: emptyList()
		}

	override val productsInList: Flow<List<ProductInListDtoSharedPrefs>>
		get() = dataStore.data.map {
			it[KEY_PRODUCT_IN_LIST]?.let { json ->
				gson.fromJson(json, TYPE_PRODUCT_IN_LIST)
			} ?: emptyList()
		}
	override val productsInCart: Flow<List<ProductInCartSharedPrefs>>
		get() = dataStore.data.map {
			it[KEY_PRODUCTS_IN_CART]?.let { json ->
				gson.fromJson(json, TYPE_PRODUCT_IN_CART)
			} ?: emptyList()
		}

	override suspend fun addProductsInList(list: List<ProductInListDtoSharedPrefs>) {
		val currentList = productsInList.first().toMutableList()
		currentList.addAll(
			list.filter { x -> currentList.find { it.guid == x.guid } == null }
		)
		dataStore.edit {
			it[KEY_PRODUCT_IN_LIST] = gson.toJson(currentList)
		}
	}

	override suspend fun addProducts(list: List<ProductDtoSharedPrefs>) {
		val currentList = products.first().toMutableList()
		currentList.addAll(
			list.filter { x -> currentList.find { it.guid == x.guid } == null }
		)
		setProductsByKey(currentList, KEY_PRODUCT)
	}
	override suspend fun incrementCounter(guid: String) {
		val newList = productsInList.first().map {
			if (it.guid == guid)
				it.copy(counter = it.counter + 1)
			else it
		}
		dataStore.edit {
			it[KEY_PRODUCT_IN_LIST] = gson.toJson(newList)
		}
	}

	override suspend fun addRandomProduct() {
		val currentList = products.first().toMutableList()
		if (currentList.isNotEmpty() && currentList.size > 5) {
			val new = currentList.random().copy(guid = UUID.randomUUID().toString())
			currentList.add(new)
			addProducts(currentList)
			addProductsInList(currentList.map { it.toProductInListDroSharedPrefs() })
		} else {
			val newProduct = ProductDtoSharedPrefs(
				guid = UUID.randomUUID().toString(),
				name = "RandomName " + System.currentTimeMillis(),
				price = Random.nextInt(1, 100_0000).toString(),
				description = "Random product descr",
				rating = Random.nextDouble(1.0, 5.0),
				isFavorite = Random.nextBoolean(),
				isInCart = false,
				images = listOf("https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg"),
				weight = null,
				count = null,
				availableCount = Random.nextInt(1, 100_0000),
				additionalParams = emptyMap()
			)
			addProducts(listOf(newProduct))
			addProductsInList(listOf(newProduct.toProductInListDroSharedPrefs()))
		}

	}


	override suspend fun toggleCart(guid: String) {
		productsInList.first().find { it.guid == guid }?.let {
			if (it.isInCart == true) {
				deleteProductFromCart(guid)
			} else {
				addProductToCart(guid)
			}
		}
	}

	private suspend fun addProductToCart(guid: String) {
		var isRunOut: Boolean = false
		val newProductList = products.first().map {
			if (it.guid == guid) {
				if (it.availableCount == 0) {
					isRunOut = true
					it.copy(isInCart = null)
				} else
					it.copy(
						isInCart = true,
						availableCount = it.availableCount - 1
					)
			} else it
		}
		val newProductsInList =
			productsInList.first().map {
				if (it.guid == guid) {
					if (isRunOut) {
						it.copy(isInCart = null)
					} else it.copy(isInCart = true)
				} else it
			}
		productsInCart.first().find { it.id == guid }?.let {
			incrementCount(guid)
		} ?: kotlin.run {
			if (isRunOut.not()){
				val newCartList = newProductsInList.find { it.guid == guid }?.let {
					listOf(it.toProductInCart()) + productsInCart.first()
				} ?: emptyList()
				setProductsByKey(newCartList, KEY_PRODUCTS_IN_CART)
			}
		}
		setProductsByKey(newProductsInList, KEY_PRODUCT_IN_LIST)
		setProductsByKey(newProductList, KEY_PRODUCT)
	}

	private suspend fun incrementCount(guid: String) {
		var isRunOut:Boolean = false
		val newProductList = products.first().map {
			if (it.guid == guid) {
				if (it.availableCount > 0)
					it.copy(availableCount = it.availableCount - 1) else {
						isRunOut = true
						it
				}
			} else it
		}
		setProductsByKey(newProductList, KEY_PRODUCT)
		val newCartList = productsInCart.first()
			.map { if (it.id == guid) {
				if (isRunOut.not())
				it.copy(amount = it.amount.inc()) else it
			} else it }

		setProductsByKey(newCartList, KEY_PRODUCTS_IN_CART)
	}

	private suspend fun decrementCount(guid: String) {
		val newCartList = productsInCart.first()
			.map {
				if (it.id == guid) {
					val amount = it.amount - 1
					if (amount == 0) {
						deleteProductFromCart(guid)
						return
					} else
						it.copy(amount = amount)
				} else it
			}
		val newProductList = products.first().map {
			if (it.guid == guid) it.copy(availableCount = it.availableCount + 1) else it
		}
		setProductsByKey(newProductList, KEY_PRODUCT)
		setProductsByKey(newCartList, KEY_PRODUCTS_IN_CART)
	}

	private suspend fun deleteProductFromCart(guid: String) {
		productsInCart.first().find { it.id == guid }?.let { deletingProduct ->
			val productList = products.first().map {
				if (it.guid == guid) it.copy(availableCount = it.availableCount + deletingProduct.amount)
				else it
			}
			setProductsByKey(productList, KEY_PRODUCT)
		}
		val productInList = productsInList.first().map {
			if (it.guid == guid) it.copy(isInCart = false) else it
		}
		setProductsByKey(productInList, KEY_PRODUCT_IN_LIST)
		val cartList = productsInCart.first().toMutableList().apply { removeIf { it.id == guid } }
		setProductsByKey(cartList, KEY_PRODUCTS_IN_CART)
	}

	override suspend fun addProductToCart(guid: String, count: Int) {
		when {
			count > 0 -> {
				addProductToCart(guid)
			}
			count < 0 -> {
				decrementCount(guid)
			}
			count == 0 -> {
				deleteProductFromCart(guid)
			}
		}
	}

	private suspend fun setProductsByKey(
		newList: List<Any>,
		key: Preferences.Key<String>
	) {
		dataStore.edit {
			it[key] = gson.toJson(newList)
		}
	}


	companion object {
		private const val PREFERENCES_PRODUCT_NAME = "Products_database"
		internal val Context.DATASTORE: DataStore<Preferences> by preferencesDataStore(
			PREFERENCES_PRODUCT_NAME
		)
		private val KEY_PRODUCT_IN_LIST = stringPreferencesKey("ProductInList")
		private val KEY_PRODUCT = stringPreferencesKey("Products")
		private val KEY_PRODUCTS_IN_CART = stringPreferencesKey("PRODUCTS_IN_CART")
		private val TYPE_PRODUCT_IN_CART =
			object : TypeToken<List<ProductInCartSharedPrefs>>() {}.type

		private val TYPE_PRODUCT = object : TypeToken<List<ProductDtoSharedPrefs>>() {}.type
		private val TYPE_PRODUCT_IN_LIST =
			object : TypeToken<List<ProductInListDtoSharedPrefs>>() {}.type


	}
}
