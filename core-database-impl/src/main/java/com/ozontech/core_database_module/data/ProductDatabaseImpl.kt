package com.ozontech.core_database_module.data

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozontech.core_database_api.ProductsDatabase
import com.ozontech.core_database_api.models.ProductDtoSharedPrefs
import com.ozontech.core_database_api.models.ProductInListDtoSharedPrefs
import javax.inject.Inject

class ProductDatabaseImpl @Inject constructor(context: Context) : ProductsDatabase {

	private val prefs = context.getSharedPreferences(PREFERENCES_PRODUCT_NAME, Context.MODE_PRIVATE)

	override fun addProductsInList(list: List<ProductInListDtoSharedPrefs>) {
		Log.e("DataBase", "list = $list")
		prefs.edit().putString(PRODUCT_IN_LIST, Gson().toJson(list)).apply()
	}

	override fun getProductsInList(): List<ProductInListDtoSharedPrefs> {
		val type = object : TypeToken<List<ProductInListDtoSharedPrefs>>() {}.type
		return prefs.getString(PRODUCT_IN_LIST, null)?.let { json ->
			Gson().fromJson(json, type)
		} ?: emptyList()
	}

	override fun addProducts(list: List<ProductDtoSharedPrefs>) {
		prefs.edit().putString(PRODUCT, Gson().toJson(list)).apply()
	}

	override fun getProducts(): List<ProductDtoSharedPrefs> {
		val type = object : TypeToken<List<ProductDtoSharedPrefs>>() {}.type
		return prefs.getString(PRODUCT, null)?.let { json ->
			Gson().fromJson(json, type)
		} ?: emptyList()
	}

	override fun getProductByGuid(guid: String): ProductDtoSharedPrefs? {
		return getProducts().find { it.guid == guid }
	}

	@RequiresApi(Build.VERSION_CODES.N)
	override fun incrementCounter(guid: String) {
		//
	}

	companion object {
		private const val PREFERENCES_PRODUCT_NAME = "Products_database"
		private const val PRODUCT_IN_LIST = "ProductInList"
		private const val PRODUCT = "Products"

	}
}