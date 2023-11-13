package com.example.mapsdemoapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mapsdemoapp.domain.location.models.LocationEntity

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<LocationEntity> //TODO: Return flow

}
