package com.example.mapsdemoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mapsdemoapp.domain.location.models.LocationEntity
import com.example.mapsdemoapp.data.database.daos.LocationDao

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class MapsDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
}
