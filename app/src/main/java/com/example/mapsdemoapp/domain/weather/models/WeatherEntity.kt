package com.example.mapsdemoapp.domain.weather.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mapsdemoapp.domain.location.models.LocationEntity

@Entity(
    tableName = "forecasts",
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = ["id"],
            childColumns = ["locationId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class WeatherResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(index = true)
    val locationId: Int,
    val coordinates: CoordinatesEntity,
    val weather: List<WeatherEntity>,
    val base: String,
    val main: MainEntity,
    val visibility: Int,
    val wind: WindEntity,
    val rain: RainEntity? = null,
    val clouds: CloudsEntity,
    val snow: SnowEntity? = null,
    val dt: Int,
    val sys: SysEntity,
    val timezone: Int,
    val weatherResponseId: Int? = null,
    val name: String? = null,
    val cod: Int,
    val timestamp: String,
)

@Entity(tableName = "coordinates")
data class CoordinatesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
)

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weatherId: Int,
    val main: String,
    val description: String,
    val icon: String,
)

@Entity(tableName = "main")
data class MainEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val groundLevel: Int,
)

@Entity(tableName = "wind")
data class WindEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val speed: Double,
    val degrees: Int,
    val gust: Double,
)

@Entity(tableName = "rain")
data class RainEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val volumeOneHour: Double? = null,
    val volumeThreeHours: Double? = null,
)

@Entity(tableName = "clouds")
data class CloudsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val all: Int,
)

@Entity(tableName = "snow")
data class SnowEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val volumeOneHour: Double? = null,
    val volumeThreeHours: Double? = null,
)

@Entity(tableName = "sys")
data class SysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: Int,
    val sysId: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
)
