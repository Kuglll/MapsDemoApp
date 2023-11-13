package com.example.mapsdemoapp.domain.location

import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.domain.location.models.LocationEntity

fun Location.toLocationEntity() = LocationEntity(
    latitude = this.latitude,
    longitude = this.longitude,
)

fun LocationEntity.toLocation() = Location(
    latitude = this.latitude,
    longitude = this.longitude,
)

