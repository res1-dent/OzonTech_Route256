package com.ozontech.homework2.data.storage

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CounterPreference(context: Application) {

    private val dataStore = context.DATASTORE


    suspend fun getCounter(guid: String): Int {
        return dataStore.data.map {
            it[intPreferencesKey(guid)] ?: 1
        }.first()
    }

    suspend fun incrementCounter(guid: String) {
        dataStore.edit {
            it[intPreferencesKey(guid)] = getCounter(guid) + 1
        }
    }


    companion object {
        private const val WORDS_DATASTORE = "counter_datastore"
        private val Context.DATASTORE: DataStore<Preferences> by preferencesDataStore(name = WORDS_DATASTORE)
    }
}