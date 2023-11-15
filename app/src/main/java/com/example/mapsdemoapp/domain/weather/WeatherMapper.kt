package com.example.mapsdemoapp.domain.weather

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
