package com.example.mapsdemoapp.domain.location

import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.domain.location.models.LocationEntity

fun Location.toLocationEntity() = LocationEntity(
    id = this.id,
    latitude = this.latitude,
    longitude = this.longitude,
    locationName = this.locationName,
)

fun LocationEntity.toLocation() = Location(
    id = this.id,
    latitude = this.latitude,
    longitude = this.longitude,
    locationName = this.locationName,
)

fun LocationEntity.toLocationWithUpdatedLocationName(
    updatedLocationName: String,
) = Location(
    id = this.id,
    latitude = this.latitude,
    longitude = this.longitude,
    locationName = updatedLocationName,
)
