package com.example.mapsdemoapp.repositories.di

import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.repositories.LocationRepositoryImpl
import com.example.mapsdemoapp.repositories.WeatherRepository
import com.example.mapsdemoapp.repositories.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ) : LocationRepository

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository

}
