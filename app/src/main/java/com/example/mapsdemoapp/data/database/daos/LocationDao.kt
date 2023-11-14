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

}
