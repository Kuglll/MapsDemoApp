package com.example.mapsdemoapp.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.mapsdemoapp.domain.weather.models.CloudsEntity
import com.example.mapsdemoapp.domain.weather.models.CoordinatesEntity
import com.example.mapsdemoapp.domain.weather.models.MainEntity
import com.example.mapsdemoapp.domain.weather.models.RainEntity
import com.example.mapsdemoapp.domain.weather.models.SnowEntity
import com.example.mapsdemoapp.domain.weather.models.SysEntity
import com.example.mapsdemoapp.domain.weather.models.WeatherEntity
import com.example.mapsdemoapp.domain.weather.models.WindEntity
import com.google.gson.Gson

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromCoordinates(coordinates: CoordinatesEntity) = Gson().toJson(coordinates)

    @TypeConverter
    fun toCoordinates(coordinates: String) = Gson().fromJson(coordinates, CoordinatesEntity::class.java)

    @TypeConverter
    fun fromWeather(weather: List<WeatherEntity>) = Gson().toJson(weather)

    @TypeConverter
    fun toWeather(weather: String) = Gson().fromJson(weather, Array<WeatherEntity>::class.java).toList()

    @TypeConverter
    fun fromMain(main: MainEntity) = Gson().toJson(main)

    @TypeConverter
    fun toMain(main: String) = Gson().fromJson(main, MainEntity::class.java)

    @TypeConverter
    fun fromWind(wind: WindEntity) = Gson().toJson(wind)

    @TypeConverter
    fun toWind(wind: String) = Gson().fromJson(wind, WindEntity::class.java)

    @TypeConverter
    fun fromRain(rain: RainEntity?): String {
        if(rain == null){
            return Gson().toJson(null)
        }
        return Gson().toJson(rain)

    }

    @TypeConverter
    fun toRain(rain: String) = Gson().fromJson(rain, RainEntity::class.java)

    @TypeConverter
    fun fromClouds(clouds: CloudsEntity) = Gson().toJson(clouds)

    @TypeConverter
    fun toClouds(clouds: String) = Gson().fromJson(clouds, CloudsEntity::class.java)

    @TypeConverter
    fun fromSnow(snow: SnowEntity?): String{
        if(snow == null){
            return Gson().toJson(null)
        }
        return Gson().toJson(snow)
    }

    @TypeConverter
    fun toSnow(snow: String) = Gson().fromJson(snow, SnowEntity::class.java)

    @TypeConverter
    fun fromSys(sys: SysEntity) = Gson().toJson(sys)

    @TypeConverter
    fun toSys(sys: String) = Gson().fromJson(sys, SysEntity::class.java)

}
