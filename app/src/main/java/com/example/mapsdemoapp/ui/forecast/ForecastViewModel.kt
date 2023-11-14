package com.example.mapsdemoapp.ui.forecast

import android.util.Log
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.ui.shared.base.BaseViewModel
import com.example.mapsdemoapp.ui.shared.di.LocationParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    @LocationParameter private val locationId: Int,
    private val locationRepository: LocationRepository,
) : BaseViewModel<ForecastState, Nothing>(ForecastState()) {

    init {
        launchWithLoading {
            //TODO: Fetch location from DB by ID. If location name is null, fetch data from api and update location. If not display data.
        }
    }

}
