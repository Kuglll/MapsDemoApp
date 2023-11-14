package com.example.mapsdemoapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mapsdemoapp.domain.location.models.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: LocationEntity)

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query("SELECT id FROM locations WHERE latitude=:latitude AND longitude=:longitude")
    fun getLocationIdByLatAndLng(latitude: Double, longitude: Double): Flow<Int>

    @Query("SELECT * FROM locations WHERE id=:id")
    fun getLocationById(id: Int): Flow<LocationEntity>

}
