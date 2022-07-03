package com.ozontech.core_database_module.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
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

	override suspend fun addProductsInList(list: List<ProductInListDtoSharedPrefs>) {
		val currentList = getProductsInList().toMutableList()
		currentList.addAll(
			list.filter { x -> currentList.find { it.guid == x.guid } == null }
		)
		dataStore.edit {
			it[KEY_PRODUCT_IN_LIST] = gson.toJson(currentList)
		}
	}

	override suspend fun getProductsInList(): List<ProductInListDtoSharedPrefs> {
		return dataStore.data.map {
			it[KEY_PRODUCT_IN_LIST]?.let { json ->
				gson.fromJson(json, TYPE_PRODUCT_IN_LIST)
			} ?: emptyList<ProductInListDtoSharedPrefs>()
		}.first()
	}

	override suspend fun addProducts(list: List<ProductDtoSharedPrefs>) {
		dataStore.edit() {
			it[KEY_PRODUCT] = gson.toJson((list + getProducts()).toSet())
		}
	}

	override suspend fun getProducts(): List<ProductDtoSharedPrefs> {
		return dataStore.data.map {
			it[KEY_PRODUCT]?.let { json ->
				gson.fromJson(json, TYPE_PRODUCT)
			} ?: emptyList<ProductDtoSharedPrefs>()
		}.first()
	}


	override suspend fun incrementCounter(guid: String) {
		val newList = getProductsInList().map {
			if (it.guid == guid)
				it.copy(counter = it.counter + 1)
			else it
		}
		dataStore.edit {
			it[KEY_PRODUCT_IN_LIST] = gson.toJson(newList)
		}
	}

	override suspend fun addRandomProduct() {
		val currentList = getProducts().toMutableList()
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
				isInCart = Random.nextBoolean(),
				images = listOf("https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg"),
				weight = null,
				count = null,
				availableCount = null,
				additionalParams = emptyMap()
			)
			addProducts(listOf(newProduct))
			addProductsInList(listOf(newProduct.toProductInListDroSharedPrefs()))
		}

	}

	override suspend fun toggleCart(guid: String, shouldInCart: Boolean) {
		val pdpProducts = getProducts().map {
			if (it.guid == guid)
				it.copy(isInCart = shouldInCart)
			else it
		}
		val listOfProducts = getProductsInList().map {
			if (it.guid == guid)
				it.copy(isInCart = shouldInCart)
			else it
		}
		dataStore.edit {
			it[KEY_PRODUCT_IN_LIST] = gson.toJson(listOfProducts)
			it[KEY_PRODUCT] = gson.toJson(pdpProducts)
		}
	}

	override suspend fun addProductToCart(guid: String, count: Int) {
		when {
			count > 0 -> {
				toggleCart(guid, true)
				decrementCount(guid)
			}
			count < 0 -> {
				incrementCount(guid)
			}
			count == 0 -> {
				toggleCart(guid, false)
				incrementCount(guid)
			}
		}
	}

	private suspend fun decrementCount(guid: String) {
		val pdpProducts = getProducts().map {
			if (it.guid == guid)
				it.copy(availableCount = it.availableCount?.dec())
			else it
		}
		dataStore.edit {
			it[KEY_PRODUCT] = gson.toJson(pdpProducts)
		}
	}

	private suspend fun incrementCount(guid: String) {
		val pdpProducts = getProducts().map {
			if (it.guid == guid)
				it.copy(availableCount = it.availableCount?.inc())
			else it
		}
		dataStore.edit {
			it[KEY_PRODUCT] = gson.toJson(pdpProducts)
		}
	}

	companion object {
		private const val PREFERENCES_PRODUCT_NAME = "Products_database"
		internal val Context.DATASTORE: DataStore<Preferences> by preferencesDataStore(
			PREFERENCES_PRODUCT_NAME
		)
		private val KEY_PRODUCT_IN_LIST = stringPreferencesKey("ProductInList")
		private val KEY_PRODUCT = stringPreferencesKey("Products")

		private val TYPE_PRODUCT = object : TypeToken<List<ProductDtoSharedPrefs>>() {}.type
		private val TYPE_PRODUCT_IN_LIST =
			object : TypeToken<List<ProductInListDtoSharedPrefs>>() {}.type


	}
}
