package com.example.mapsdemoapp.ui.forecast

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mapsdemoapp.repositories.LocationRepository
import com.example.mapsdemoapp.repositories.WeatherRepository
import com.example.mapsdemoapp.ui.shared.base.BaseViewModel
import com.example.mapsdemoapp.ui.shared.di.LocationParameter
import com.example.mapsdemoapp.utils.extensions.limitDecimals
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ForecastViewModel @Inject constructor(
    @LocationParameter private val locationId: Int,
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
) : BaseViewModel<ForecastState, ForecastEvent>(ForecastState()) {

    init {
        fetchLocationById()
    }

    private fun fetchLocationById() {
        locationRepository.getLocationById(locationId).onEach { location ->
            updateState { state ->
                state.copy(
                    locationName = location.locationName ?: "No data",
                    latutide = location.latitude.limitDecimals(),
                    longitude = location.longitude.limitDecimals(),
                )
            }
            location.locationName?.let {
                fetchWeatherData(it)
            }
        }.catch {
            showError(it.message)
        }.launchIn(viewModelScope)
    }

    private suspend fun fetchWeatherData(locationName: String) {
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
                    iconId = weather.iconId,
                    lastFetchedTime = weather.lastFetchedTime,
                )
            }
        }.catch {
            showError(it.message)
        }.launchIn(viewModelScope)
    }

    fun onDeleteLocationClicked() {
        launchWithLoading {
            val numberOfDeletedLocations = locationRepository.deleteLocationById(locationId)
            if (numberOfDeletedLocations > 0) {
                postEvent(ForecastEvent.LocationDeletedSuccessfully)
            }
        }
    }

}
