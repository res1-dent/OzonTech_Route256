package com.ozontech.homework2.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.ozontech.homework2.data.dto.ProductDto
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun parseJsonToProduct(context: Context) : List<ProductDto> {
    getJsonDataFromAsset(context, "Product.json")?.let { data ->
        val gson = GsonBuilder().create()
        return gson.fromJson(data, Array<ProductDto>::class.java).toList()
    } ?: return emptyList()
}