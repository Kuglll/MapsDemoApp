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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.ui.shared.base.BaseComposable

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel(),
    onBackButtonClicked: () -> Unit,
) {
    BaseComposable(
        viewModel = viewModel,
        eventsHandler = { event ->
            when(event){
                is ForecastEvent.LocationDeletedSuccessfully -> {
                    onBackButtonClicked()
                }
            }
        }
    ) { state ->
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
                    text = state.locationName,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "${state.latutide}, ${state.longitude}")
                Spacer(modifier = Modifier.height(16.dp))
                WeatherCard(
                    currentTemperature = state.temperature.toString(),
                    minTemperature = state.minTemperature.toString(),
                    maxTemperature = state.maxTemperature.toString(),
                    precipitation = state.rainAmount?.toString(),
                    airPressure = state.pressure.toString(),
                    humidity = state.humidity.toString(),
                    windSpeed = state.windSpeed.toString(),
                    iconId = state.iconId,
                    lastFetchedInfo = state.lastFetchedTime,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = viewModel::onDeleteLocationClicked,
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
}
