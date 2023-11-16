package com.example.mapsdemoapp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mapbox.maps.Style
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull

interface DataStoreSource {

    suspend fun storePreferredMapStyle(mapString: String)

    suspend fun getPreferredMapStyle(): String

}

class DataStoreSourceImpl @Inject constructor(
    private val store: DataStore<Preferences>,
): DataStoreSource {

    companion object{
        private val PREFERRED_MAP_STYLE = stringPreferencesKey("preferred_map_style")
    }

    override suspend fun storePreferredMapStyle(mapString: String) {
        store.edit { prefs ->
            prefs[PREFERRED_MAP_STYLE] = mapString
        }
    }

    override suspend fun getPreferredMapStyle(): String =
        store.data.firstOrNull()
            ?.let { prefs -> prefs[PREFERRED_MAP_STYLE] }
            ?: Style.SATELLITE_STREETS

}
