package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.database.daos.LocationDao
import com.example.mapsdemoapp.data.network.NominatimService
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.domain.location.models.LocationEntity
import com.example.mapsdemoapp.domain.location.toLocation
import com.example.mapsdemoapp.domain.location.toLocationEntity
import com.example.mapsdemoapp.domain.location.toLocationWithUpdatedLocationName
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface LocationRepository {

    suspend fun storeLocation(location: Location)

    suspend fun updateLocation(location: Location)

    fun getLocationIdByLatAndLng(location: Location): Flow<Int>

    fun getLocationById(id: Int): Flow<Location>

    fun getLocations(): Flow<List<Location>>

}

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val nominatimService: NominatimService,
) : LocationRepository {

    override suspend fun storeLocation(location: Location) {
        locationDao.insertLocation(location.toLocationEntity())
    }

    override suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location.toLocationEntity())
    }

    override fun getLocationIdByLatAndLng(location: Location): Flow<Int> = locationDao.getLocationIdByLatAndLng(
        latitude = location.latitude,
        longitude = location.longitude,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getLocationById(id: Int): Flow<Location> {
        return locationDao.getLocationById(id).flatMapConcat { location ->
            if (location.locationName.isNullOrEmpty()) {
                fetchAndStoreLocationName(location)
            } else {
                flowOf(location.toLocation())
            }
        }
    }

    private fun fetchAndStoreLocationName(location: LocationEntity): Flow<Location> = flow {
        val locationName = fetchLocationName(location.latitude, location.longitude)
        val updatedLocation = location.toLocationWithUpdatedLocationName(locationName)
        updateLocation(updatedLocation)
    }

    /**
     * Some locations return empty city, we return village in that case.
     */
    private suspend fun fetchLocationName(
        latitude: Double,
        longitude: Double,
    ): String{
        val place = nominatimService.getLocationNameByLatAndLng(
            latitude = latitude,
            longitude = longitude,
        )
        if(place.address.city.isNotEmpty()){
            return place.address.city
        }
        return place.address.village
    }

    override fun getLocations(): Flow<List<Location>> =
        locationDao.getAllLocations().map { locations ->
            locations.map { locationEntity ->
                locationEntity.toLocation()
            }
        }

}
