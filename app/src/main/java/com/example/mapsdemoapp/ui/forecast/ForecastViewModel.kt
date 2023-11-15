package com.example.mapsdemoapp.ui.forecast

import androidx.lifecycle.viewModelScope
import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.repositories.WeatherRepository
import com.example.mapsdemoapp.ui.shared.base.BaseViewModel
import com.example.mapsdemoapp.ui.shared.di.LocationParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ForecastViewModel @Inject constructor(
    @LocationParameter private val locationId: Int,
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
) : BaseViewModel<ForecastState, Nothing>(
    ForecastState(
        temperature = 0,
        minTemperature = 0,
        maxTemperature = 0,
        humidity = 0,
        windSpeed = 0,
        lastFetchedTime = "",
        pressure = 0,
        locationName = "",
        latutide = 0.0,
        longitude = 0.0 //TODO: Remove this initial state
    )
) {

    init {
        fetchLocationById()
    }

    private fun fetchLocationById(){
        locationRepository.getLocationById(locationId).onEach { location ->
            updateState { state ->
                state.copy(
                    locationName = location.locationName ?: "No data",
                    latutide = location.latitude,
                    longitude = location.longitude,
                )
            }
            location.locationName?.let {
                fetchWeatherData(it)
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun fetchWeatherData(locationName: String){
        weatherRepository.getWeatherByCityName(locationName, locationId).onEach { weather ->
            updateState { state ->
                state.copy(
                    temperature = weather.temperature,
                    minTemperature = weather.minTemperature,
                    maxTemperature = weather.maxTemperature,
                    rainAmount = weather.rainAmount,
                    pressure = weather.pressure,
                    humidity = weather.humidity,
                    windSpeed = weather.windSpeed,
                    lastFetchedTime = weather.lastFetchedTime,
                )
            }
        }.launchIn(viewModelScope)
    }

}
