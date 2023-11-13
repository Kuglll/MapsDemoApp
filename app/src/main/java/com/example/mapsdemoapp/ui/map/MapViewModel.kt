package com.example.mapsdemoapp.ui.map

import androidx.lifecycle.viewModelScope
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.ui.shared.base.BaseViewModel
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
) : BaseViewModel<MapState, Nothing>(MapState()) {

    init {
        locationRepository.getLocations().map {
            updateState { state ->
                state.copy(savedLocations = it)
            }
        }.launchIn(viewModelScope)
    }

    fun onLongPress(point: Point) {
        launchWithLoading {
            locationRepository.storeLocation(point.toLocation())
        }
    }

    fun onToggleMapTypeClicked() {
        when (state.value.currentMapStyle) {
            Style.OUTDOORS -> {
                updateState { state ->
                    state.copy(currentMapStyle = Style.SATELLITE_STREETS)
                }
            }
            Style.SATELLITE_STREETS -> {
                updateState { state ->
                    state.copy(currentMapStyle = Style.OUTDOORS)
                }
            }
        }
    }

    private fun Point.toLocation() = Location(
        latitude = this.latitude(),
        longitude = this.longitude(),
    )

}
