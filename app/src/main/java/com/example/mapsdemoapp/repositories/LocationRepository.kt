package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.database.daos.LocationDao
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.domain.location.toLocation
import com.example.mapsdemoapp.domain.location.toLocationEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LocationRepository {

    suspend fun storeLocation(location: Location)

    suspend fun updateLocation(location: Location)

    fun getLocationIdByLatAndLng(location: Location): Int

    fun getLocations(): Flow<List<Location>>

}

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao
) : LocationRepository {

    override suspend fun storeLocation(location: Location) {
        locationDao.insertLocation(location.toLocationEntity())
    }

    override suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location.toLocationEntity())
    }

    override fun getLocationIdByLatAndLng(location: Location): Int = locationDao.getLocationIdByLatAndLng(
        latitude = location.latitude,
        longitude = location.longitude,
    )

    override fun getLocations(): Flow<List<Location>> =
        locationDao.getAllLocations().map { locations ->
            locations.map { locationEntity ->
                locationEntity.toLocation()
            }
        }

}
