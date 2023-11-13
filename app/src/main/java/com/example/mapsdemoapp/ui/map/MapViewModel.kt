package com.example.mapsdemoapp.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.repositories.LocationRepository
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MapState(
    val locations: List<Location> = emptyList()
)

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
) : ViewModel() {

    val state: StateFlow<MapState> get() = stateFlow.asStateFlow()
    private val stateFlow: MutableStateFlow<MapState> by lazy { MutableStateFlow(MapState()) }

    init {
        viewModelScope.launch {
            stateFlow.update {
                it.copy(locations = locationRepository.getLocations())
            }
        }
    }

    fun onLongPress(point: Point){
        viewModelScope.launch {//TODO: Create base viewmodel
            locationRepository.storeLocation(point.toLocation())
        }
    }

    private fun Point.toLocation() = Location(
        latitude = this.latitude(),
        longitude = this.longitude(),
    )

}
