package com.example.mapsdemoapp.data.network

import com.example.mapsdemoapp.data.network.models.Place
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimService{

    @GET("reverse")
    suspend fun getLocationNameByLatAndLng(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
    ) : Place

}
