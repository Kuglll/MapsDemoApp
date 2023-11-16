package com.example.mapsdemoapp.data.datastore.di

import com.example.mapsdemoapp.data.datastore.DataStoreSource
import com.example.mapsdemoapp.data.datastore.DataStoreSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataStoreSourceModule {

    @Binds
    abstract fun bindDataStoreSource(
        dataStoreSourceImpl: DataStoreSourceImpl
    ) : DataStoreSource

}
