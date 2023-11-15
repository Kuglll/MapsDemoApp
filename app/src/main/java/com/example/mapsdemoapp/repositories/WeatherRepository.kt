package com.example.mapsdemoapp.repositories

import com.example.mapsdemoapp.data.database.daos.WeatherDao
import com.example.mapsdemoapp.data.network.OpenWeatherService
import com.example.mapsdemoapp.domain.weather.models.Weather
import com.example.mapsdemoapp.domain.weather.toWeather
import com.example.mapsdemoapp.domain.weather.toWeatherResponseEntity
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
        return if(currentWeather == null){
            val weatherResponse = openWeatherService.getWeatherByCityName(cityName = cityName)
            weatherDao.insertWeatherResponse(weatherResponse = weatherResponse.toWeatherResponseEntity(locationId))
            flowOf(weatherResponse.toWeather())
        } else {
            flowOf(currentWeather.toWeather())
        }
    }

}
