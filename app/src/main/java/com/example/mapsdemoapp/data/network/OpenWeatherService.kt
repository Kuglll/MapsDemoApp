package com.example.mapsdemoapp.data.network

import com.example.mapsdemoapp.BuildConfig
import com.example.mapsdemoapp.data.network.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService{

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric",
    ) : WeatherResponse

}
