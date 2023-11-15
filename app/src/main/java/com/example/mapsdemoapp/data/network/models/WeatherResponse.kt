package com.example.mapsdemoapp.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("main")
    val main: Main,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("snow")
    val snow: Snow,
    @SerializedName("dt")
    val dt: Int? = null,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("cod")
    val cod: Int? = null,
)

data class Coordinates(
    @SerializedName("lon")
    val longitude: Double? = null,
    @SerializedName("lat")
    val latitude: Double? = null,
)

data class Weather(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String,
)

data class Main(
    @SerializedName("temp")
    val temperature: Double? = null,
    @SerializedName("feels_like")
    val feelsLike: Double? = null,
    @SerializedName("temp_min")
    val minTemperature: Double? = null,
    @SerializedName("temp_max")
    val maxTemperature: Double? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    @SerializedName("grnd_level")
    val groundLevel: Int? = null,
)

data class Wind(
    @SerializedName("speed")
    val speed: Double? = null,
    @SerializedName("deg")
    val degrees: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null,
)

data class Rain(
    @SerializedName("1h")
    val volume1h: Double? = null,
    @SerializedName("3h")
    val volume3h: Double? = null,
)

data class Clouds(
    @SerializedName("all")
    val all: Int? = null,
)

data class Snow(
    @SerializedName("1h")
    val volume1h: Double? = null,
    @SerializedName("3h")
    val volume3h: Double? = null,
)

data class Sys(
    @SerializedName("type")
    val type: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null,
)
