package com.example.mapsdemoapp.domain.weather

import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.data.network.models.Clouds
import com.example.mapsdemoapp.data.network.models.Coordinates
import com.example.mapsdemoapp.data.network.models.Main
import com.example.mapsdemoapp.data.network.models.Rain
import com.example.mapsdemoapp.data.network.models.Snow
import com.example.mapsdemoapp.data.network.models.Sys
import com.example.mapsdemoapp.data.network.models.Weather
import com.example.mapsdemoapp.data.network.models.WeatherResponse
import com.example.mapsdemoapp.data.network.models.Wind
import com.example.mapsdemoapp.domain.weather.models.CloudsEntity
import com.example.mapsdemoapp.domain.weather.models.CoordinatesEntity
import com.example.mapsdemoapp.domain.weather.models.MainEntity
import com.example.mapsdemoapp.domain.weather.models.RainEntity
import com.example.mapsdemoapp.domain.weather.models.SnowEntity
import com.example.mapsdemoapp.domain.weather.models.SysEntity
import com.example.mapsdemoapp.domain.weather.models.WeatherEntity
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity
import com.example.mapsdemoapp.domain.weather.models.WindEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

typealias WeatherDomain = com.example.mapsdemoapp.domain.weather.models.Weather

fun WeatherResponseEntity.toWeather() = WeatherDomain(
    temperature = this.main.temperature.roundToInt(),
    minTemperature = this.main.minTemperature.roundToInt(),
    maxTemperature = this.main.maxTemperature.roundToInt(),
    rainAmount = this.rain?.volumeOneHour,
    pressure = this.main.pressure,
    humidity = this.main.humidity,
    windSpeed = this.wind.speed.roundToInt(),
    iconId = this.weather[0].icon.toIconId(),
    lastFetchedTime = this.timestamp,
)

fun WeatherResponse.toWeather() = WeatherDomain(
    temperature = this.main.temperature.roundToInt(),
    minTemperature = this.main.minTemperature.roundToInt(),
    maxTemperature = this.main.maxTemperature.roundToInt(),
    rainAmount = this.rain?.volumeOneHour,
    pressure = this.main.pressure,
    humidity = this.main.humidity,
    windSpeed = this.wind.speed.roundToInt(),
    iconId = this.weather[0].icon.toIconId(),
    lastFetchedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")),
)

fun String.toIconId() = when (this) {
    "01d" -> R.drawable.ic_01d
    "02d" -> R.drawable.ic_02d
    "03d", "03n" -> R.drawable.ic_03d
    "04d", "04n" -> R.drawable.ic_04d
    "09d", "09n" -> R.drawable.ic_09d
    "10d" -> R.drawable.ic_10d
    "11d", "11n" -> R.drawable.ic_11d
    "13d", "13n" -> R.drawable.ic_13d
    "50d", "50n" -> R.drawable.ic_50d
    "01n" -> R.drawable.ic_01n
    "02n" -> R.drawable.ic_02n
    "10n" -> R.drawable.ic_10n
    else -> R.drawable.ic_01d
}

fun WeatherResponse.toWeatherResponseEntity(
    locationId: Int,
) = WeatherResponseEntity(
    locationId = locationId,
    coordinates = this.coordinates.toCoordinatesEntity(),
    weather = this.weather.map { it.toWeatherEntity() },
    base = this.base,
    main = this.main.toMainEntity(),
    visibility = this.visibility,
    wind = this.wind.toWindEntity(),
    rain = this.rain?.toRainEntity(),
    clouds = this.clouds.toCloudsEntity(),
    snow = this.snow?.toSnowEntity(),
    dt = this.dt,
    sys = this.sys.toSysEntity(),
    timezone = this.timezone,
    weatherResponseId = this.id,
    name = this.name,
    cod = this.cod,
    timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")),
)

fun Coordinates.toCoordinatesEntity() = CoordinatesEntity(
    latitude = this.latitude,
    longitude = this.longitude,
)

fun Weather.toWeatherEntity() = WeatherEntity(
    weatherId = this.id,
    main = this.main,
    description = this.description,
    icon = this.icon,
)

fun Main.toMainEntity() = MainEntity(
    temperature = this.temperature,
    feelsLike = this.feelsLike,
    minTemperature = this.minTemperature,
    maxTemperature = this.maxTemperature,
    pressure = this.pressure,
    humidity = this.humidity,
    seaLevel = this.seaLevel,
    groundLevel = this.groundLevel,
)

fun Wind.toWindEntity() = WindEntity(
    speed = this.speed,
    degrees = this.degrees,
    gust = this.gust,
)

fun Rain.toRainEntity() = RainEntity(
    volumeOneHour = this.volumeOneHour,
    volumeThreeHours = this.volumeThreeHours,
)

fun Clouds.toCloudsEntity() = CloudsEntity(
    all = this.all,
)

fun Snow.toSnowEntity() = SnowEntity(
    volumeOneHour = this.volumeOneHour,
    volumeThreeHours = this.volumeThreeHours,
)

fun Sys.toSysEntity() = SysEntity(
    type = this.type,
    sysId = this.id,
    country = this.country,
    sunrise = this.sunrise,
    sunset = this.sunset,
)
