package com.example.mapsdemoapp.domain.weather.models

data class Weather(
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val rainAmount: Double? = null,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Int,
    val iconId: Int,
    val lastFetchedTime: String,
)
