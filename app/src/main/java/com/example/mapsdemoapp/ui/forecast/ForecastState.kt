package com.example.mapsdemoapp.ui.forecast

data class ForecastState(
    val locationName: String = "",
    val latutide: Double, //TODO: Round this to 6 decimals
    val longitude: Double,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val rainAmount: Double? = null,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Int,
    val lastFetchedTime: String,
)
