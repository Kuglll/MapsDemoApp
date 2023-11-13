package com.example.mapsdemoapp.repositories.di

import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.repositories.LocationRepositoryImpl
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

}
