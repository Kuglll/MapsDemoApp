package com.example.mapsdemoapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mapsdemoapp.domain.weather.models.CloudsEntity
import com.example.mapsdemoapp.domain.weather.models.CoordinatesEntity
import com.example.mapsdemoapp.domain.weather.models.MainEntity
import com.example.mapsdemoapp.domain.weather.models.RainEntity
import com.example.mapsdemoapp.domain.weather.models.SnowEntity
import com.example.mapsdemoapp.domain.weather.models.SysEntity
import com.example.mapsdemoapp.domain.weather.models.WeatherEntity
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity
import com.example.mapsdemoapp.domain.weather.models.WindEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    suspend fun insertWeatherResponse(weatherResponse: WeatherResponseEntity)

    @Query("UPDATE forecasts SET coordinates = :coordinates," +
        "weather = :weather," +
        "base = :base," +
        "main = :main," +
        "visibility = :visibility," +
        "wind = :wind," +
        "rain = :rain," +
        "clouds = :clouds," +
        "snow = :snow," +
        "dt = :dt," +
        "sys = :sys," +
        "timezone = :timezone," +
        "weatherResponseId = :weatherResponseId," +
        "name = :name," +
        "cod = :cod," +
        "timestamp = :timestamp" +
        " WHERE locationId = :locationId")
    suspend fun updateWeatherResponse(
        coordinates: CoordinatesEntity,
        weather: List<WeatherEntity>,
        base: String,
        main: MainEntity,
        visibility: Int,
        wind: WindEntity,
        rain: RainEntity?,
        clouds: CloudsEntity,
        snow: SnowEntity?,
        dt: Int,
        sys: SysEntity,
        timezone: Int,
        weatherResponseId: Int?,
        name: String?,
        cod: Int?,
        timestamp: String,
        locationId: Int,
    )

    @Query("SELECT * FROM forecasts WHERE locationId = :locationId")
    fun getWeatherByLocationId(locationId: Int): Flow<WeatherResponseEntity>

}
