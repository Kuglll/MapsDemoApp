package com.example.mapsdemoapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeatherResponse(weatherResponse: WeatherResponseEntity)

    @Query("SELECT * FROM forecasts WHERE locationId = :locationId")
    fun getWeatherByLocationId(locationId: Int): Flow<WeatherResponseEntity>

}
