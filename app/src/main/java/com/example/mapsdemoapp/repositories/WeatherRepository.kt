package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.database.daos.WeatherDao
import com.example.mapsdemoapp.data.network.OpenWeatherService
import com.example.mapsdemoapp.data.network.models.WeatherResponse
import com.example.mapsdemoapp.domain.weather.LAST_FETCHED_TIME_FORMATTER
import com.example.mapsdemoapp.domain.weather.models.Weather
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity
import com.example.mapsdemoapp.domain.weather.toCloudsEntity
import com.example.mapsdemoapp.domain.weather.toCoordinatesEntity
import com.example.mapsdemoapp.domain.weather.toMainEntity
import com.example.mapsdemoapp.domain.weather.toRainEntity
import com.example.mapsdemoapp.domain.weather.toSnowEntity
import com.example.mapsdemoapp.domain.weather.toSysEntity
import com.example.mapsdemoapp.domain.weather.toWeather
import com.example.mapsdemoapp.domain.weather.toWeatherEntity
import com.example.mapsdemoapp.domain.weather.toWeatherResponseEntity
import com.example.mapsdemoapp.domain.weather.toWindEntity
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

interface WeatherRepository {

    suspend fun getWeatherByCityName(
        cityName: String,
        locationId: Int,
    ): Flow<Weather>

}

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val weatherDao: WeatherDao,
) : WeatherRepository {

    override suspend fun getWeatherByCityName(cityName: String, locationId: Int): Flow<Weather> {
        val currentWeather = weatherDao.getWeatherByLocationId(locationId).firstOrNull()

        return if (currentWeather != null && checkIfWeatherDataNeedsToBeUpdated(currentWeather)) {
            // Data is more than an hour old. We need to fetch new data.
            val weatherResponse = openWeatherService.getWeatherByCityName(cityName = cityName)
            updateWeatherDataInDatabase(weatherResponse, locationId)
            flowOf(weatherResponse.toWeather())
        } else if (currentWeather == null) {
            // We don't have data. We need to fetch it.
            val weatherResponse = openWeatherService.getWeatherByCityName(cityName = cityName)
            weatherDao.insertWeatherResponse(weatherResponse = weatherResponse.toWeatherResponseEntity(locationId))
            flowOf(weatherResponse.toWeather())
        } else {
            // Data is still fresh. Return data from the database.
            flowOf(currentWeather.toWeather())
        }
    }

    private fun checkIfWeatherDataNeedsToBeUpdated(currentWeather: WeatherResponseEntity): Boolean {
        val storedTime = LocalDateTime.parse(currentWeather.timestamp, LAST_FETCHED_TIME_FORMATTER)
        val hourDifference = storedTime.until(LocalDateTime.now(), ChronoUnit.HOURS)
        return hourDifference > 0
    }

    private suspend fun updateWeatherDataInDatabase(weatherResponse: WeatherResponse, locationId: Int){
        weatherDao.updateWeatherResponse(
            coordinates = weatherResponse.coordinates.toCoordinatesEntity(),
            weather = weatherResponse.weather.map { it.toWeatherEntity() },
            base = weatherResponse.base,
            main = weatherResponse.main.toMainEntity(),
            visibility = weatherResponse.visibility,
            wind = weatherResponse.wind.toWindEntity(),
            rain = weatherResponse.rain?.toRainEntity(),
            clouds = weatherResponse.clouds.toCloudsEntity(),
            snow = weatherResponse.snow?.toSnowEntity(),
            dt = weatherResponse.dt,
            sys = weatherResponse.sys.toSysEntity(),
            timezone = weatherResponse.timezone,
            weatherResponseId = weatherResponse.id,
            name = weatherResponse.name,
            cod = weatherResponse.cod,
            timestamp = LocalDateTime.now().format(LAST_FETCHED_TIME_FORMATTER),
            locationId = locationId,
        )
    }

}
