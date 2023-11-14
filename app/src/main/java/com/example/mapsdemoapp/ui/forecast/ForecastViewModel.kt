package com.example.mapsdemoapp.ui.forecast

import androidx.lifecycle.viewModelScope
import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.ui.shared.base.BaseViewModel
import com.example.mapsdemoapp.ui.shared.di.LocationParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@HiltViewModel
class ForecastViewModel @Inject constructor(
    @LocationParameter private val locationId: Int,
    private val locationRepository: LocationRepository,
) : BaseViewModel<ForecastState, Nothing>(ForecastState()) {

    init {
        locationRepository.getLocationById(locationId).map { currentLocation ->
            updateState { state ->
                state.copy(
                    locationName = currentLocation.locationName ?: "No data"
                )
            }
        }.launchIn(viewModelScope)
    }

}
