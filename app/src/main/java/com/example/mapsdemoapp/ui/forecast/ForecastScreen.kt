package com.example.mapsdemoapp.ui.forecast

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mapsdemoapp.R

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel(),
    onBackButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
                text = "Ljubljana",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(text = "14.482543, 46.036905")
            Spacer(modifier = Modifier.height(16.dp))
            WeatherCard(
                currentTemperature = "11°",
                minTemperature = "4°",
                maxTemperature = "13°",
                precipitation = "3.16 mm/h",
                airPressure = "1015 hPa",
                humidity = "65%",
                windSpeed = "13 m/s",
                lastFetchedInfo = "Last fetched: 14:25 13.11.2023",
            )
        }
    }
}

@Composable
fun WeatherCard(
    currentTemperature: String,
    minTemperature: String,
    maxTemperature: String,
    precipitation: String,
    airPressure: String,
    humidity: String,
    windSpeed: String,
    lastFetchedInfo: String,
) {
    Card(
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp, bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = currentTemperature,
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_marker_blue),
                        contentDescription = stringResource(id = R.string.weather_image_content_description),
                        modifier = Modifier.size(width = 200.dp, height = 100.dp),
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = maxTemperature,
                            style = MaterialTheme.typography.h6,
                        )
                        Text(
                            text = minTemperature,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 32.dp),
                ) {
                    WeatherInfoComponent(titleId = R.string.precipitation, value = precipitation)
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponent(titleId = R.string.air_pressure, value = airPressure)
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponent(titleId = R.string.humidity, value = humidity)
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponent(titleId = R.string.wind_speed, value = windSpeed)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = lastFetchedInfo,
                style = MaterialTheme.typography.caption,
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun WeatherInfoComponent(
    @StringRes titleId: Int,
    value: String,
) {
    Text(
        text = stringResource(id = titleId),
        style = MaterialTheme.typography.body2,
        color = Color.Gray,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = value,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.SemiBold,
    )
}
