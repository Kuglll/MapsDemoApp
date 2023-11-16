package com.example.mapsdemoapp.ui.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.ui.shared.base.BaseComposable
import com.example.mapsdemoapp.ui.theme.MapsTheme

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel(),
    onBackButtonClicked: () -> Unit,
) {
    BaseComposable(
        viewModel = viewModel,
        eventsHandler = { event ->
            when (event) {
                is ForecastEvent.LocationDeletedSuccessfully -> {
                    onBackButtonClicked()
                }
            }
        }
    ) { state ->
        ForecastScreenContent(
            locationName = state.locationName,
            latitude = state.latutide,
            longitude = state.longitude,
            temperature = state.temperature,
            minTemperature = state.minTemperature,
            maxTemperature = state.maxTemperature,
            rainAmount = state.rainAmount,
            pressure = state.pressure,
            humidity = state.humidity,
            windSpeed = state.windSpeed,
            iconId = state.iconId,
            lastFetchedTime = state.lastFetchedTime,
            onBackButtonClicked = onBackButtonClicked,
            onDeleteLocationClicked = viewModel::onDeleteLocationClicked,
        )
    }
}

@Composable
fun ForecastScreenContent(
    locationName: String,
    latitude: String,
    longitude: String,
    temperature: Int,
    minTemperature: Int,
    maxTemperature: Int,
    rainAmount: Double?,
    pressure: Int,
    humidity: Int,
    windSpeed: Int,
    iconId: Int?,
    lastFetchedTime: String,
    onBackButtonClicked: () -> Unit,
    onDeleteLocationClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar {
            TextButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = onBackButtonClicked,
            ) {
                Text(
                    text = stringResource(R.string.back),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = locationName,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(text = "$latitude, $longitude")
            Spacer(modifier = Modifier.height(16.dp))
            WeatherCard(
                currentTemperature = temperature.toString(),
                minTemperature = minTemperature.toString(),
                maxTemperature = maxTemperature.toString(),
                precipitation = rainAmount?.toString(),
                airPressure = pressure.toString(),
                humidity = humidity.toString(),
                windSpeed = windSpeed.toString(),
                iconId = iconId,
                lastFetchedInfo = lastFetchedTime,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onDeleteLocationClicked,
            modifier = Modifier.padding(bottom = 16.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = null,
        ) {
            Text(
                text = stringResource(id = R.string.delete_location),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenContentPreview() {
    MapsTheme {
        ForecastScreenContent(
            locationName = "London",
            latitude = "51.51",
            longitude = "-0.13",
            temperature = 10,
            minTemperature = 8,
            maxTemperature = 12,
            rainAmount = 0.0,
            pressure = 1000,
            humidity = 80,
            windSpeed = 10,
            iconId = R.drawable.ic_01d,
            lastFetchedTime = "2021-09-12 12:00",
            onBackButtonClicked = {},
            onDeleteLocationClicked = {},
        )
    }
}
