package com.example.mapsdemoapp.ui.forecast

data class ForecastState(
    val locationName: String = "",
    val latutide: Double = 0.0, //TODO: Round this to 6 decimals
    val longitude: Double = 0.0,
    val temperature: Int = 0,
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val rainAmount: Double? = null,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Int = 0,
    val lastFetchedTime: String = "",
)
