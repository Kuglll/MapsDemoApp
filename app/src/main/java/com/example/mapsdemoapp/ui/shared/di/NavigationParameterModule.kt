package com.example.mapsdemoapp.ui.shared.di

import androidx.lifecycle.SavedStateHandle
import com.example.mapsdemoapp.ui.shared.NavigationParameter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocationParameter

@Module
@InstallIn(ViewModelComponent::class)
class NavigationParameterModule {

    @Provides
    @LocationParameter
    @ViewModelScoped
    fun provideLocationIdParameter(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle.get<Int>(NavigationParameter.LOCATION_ID_PARAMETER)
            ?: throw IllegalArgumentException("Could not get the 'locationId' parameter")

}
