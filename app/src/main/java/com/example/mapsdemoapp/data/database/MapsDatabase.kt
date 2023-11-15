package com.example.mapsdemoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mapsdemoapp.domain.location.models.LocationEntity
import com.example.mapsdemoapp.data.database.daos.LocationDao
import com.example.mapsdemoapp.data.database.daos.WeatherDao
import com.example.mapsdemoapp.domain.weather.models.WeatherResponseEntity

@Database(entities = [LocationEntity::class, WeatherResponseEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MapsDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    abstract fun weatherDao(): WeatherDao
}
