package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.database.daos.LocationDao
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.domain.location.toLocation
import com.example.mapsdemoapp.domain.location.toLocationEntity
import javax.inject.Inject


interface LocationRepository {

    suspend fun storeLocation(location: Location)

    suspend fun getLocations(): List<Location>

}

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao
) : LocationRepository {

    override suspend fun storeLocation(location: Location) {
        locationDao.insertLocation(location.toLocationEntity())
    }

    override suspend fun getLocations(): List<Location> =
        locationDao.getAllLocations().map {
            it.toLocation()
        }

}
