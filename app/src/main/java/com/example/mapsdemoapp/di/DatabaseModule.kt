package com.example.mapsdemoapp.di

import android.content.Context
import androidx.room.Room
import com.example.mapsdemoapp.data.database.MapsDatabase
import com.example.mapsdemoapp.data.database.daos.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MapsDatabase {
        return Room.databaseBuilder(
            context,
            MapsDatabase::class.java,
            "maps_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocationDao(mapsDatabase: MapsDatabase): LocationDao {
        return mapsDatabase.locationDao()
    }

}
