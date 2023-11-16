package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.datastore.DataStoreSource
import javax.inject.Inject

interface UserSettingsRepository {

    suspend fun getPreferredMapStyle(): String

    suspend fun storePreferredMapStyle(mapStyle: String)

}

class UserSettingsRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource,
) : UserSettingsRepository{

    override suspend fun getPreferredMapStyle() = dataStoreSource.getPreferredMapStyle()

    override suspend fun storePreferredMapStyle(mapStyle: String) {
        dataStoreSource.storePreferredMapStyle(mapStyle)
    }

}
