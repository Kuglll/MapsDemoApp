package com.example.mapsdemoapp.domain.location.models

data class Location(
    val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val locationName: String? = null,
)
