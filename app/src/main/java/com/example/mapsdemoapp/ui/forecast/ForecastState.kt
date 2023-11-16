package com.example.mapsdemoapp.ui.forecast

data class ForecastState(
    val locationName: String = "",
    val latutide: String = "0.0",
    val longitude: String = "0.0",
    val temperature: Int = 0,
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val rainAmount: Double? = null,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Int = 0,
    val lastFetchedTime: String = "",
)
