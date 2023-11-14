package com.example.mapsdemoapp.data.network.di

import com.example.mapsdemoapp.data.network.NominatimService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val NOMINATIM_RETROFIT = "NOMINATIM_RETROFIT"

    @Provides
    @Singleton
    @Named(NOMINATIM_RETROFIT)
    fun provideNominatimRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNominatimService(@Named(NOMINATIM_RETROFIT) retrofit: Retrofit): NominatimService {
        return retrofit.create(NominatimService::class.java)
    }

}
