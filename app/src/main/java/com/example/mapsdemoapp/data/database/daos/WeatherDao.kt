package com.example.mapsdemoapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeatherResponse(weatherResponse: WeatherResponseEntity)

}
