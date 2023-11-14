package com.example.mapsdemoapp.ui.map

import com.example.mapsdemoapp.domain.location.models.Location
import com.mapbox.maps.Style

data class MapState(
    val savedLocations: List<Location> = emptyList(),
    val currentMapStyle: String = Style.OUTDOORS,
)

sealed interface MapEvent {
    data class LocationIdRetrievedSuccessfully(
        val locationId: Int,
    ) : MapEvent
}
