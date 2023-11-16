package com.example.mapsdemoapp.ui.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mapsdemoapp.R

@Composable
fun WeatherCard(
    currentTemperature: String,
    minTemperature: String,
    maxTemperature: String,
    precipitation: String?,
    airPressure: String,
    humidity: String,
    windSpeed: String,
    iconId: Int?,
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
                    BigTemperatureItem(
                        temperatureValue = currentTemperature,
                    )
                    Image(
                        painter = painterResource(id = iconId ?: R.drawable.ic_01d),
                        contentDescription = stringResource(id = R.string.weather_image_content_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.defaultMinSize(minWidth = 130.dp),
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        SmallTemperatureItem(temperatureValue = maxTemperature)
                        SmallTemperatureItem(temperatureValue = minTemperature)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 32.dp),
                ) {
                    WeatherInfoComponentView(
                        WeatherInfoComponent.Precipitation(
                            titleId = R.string.precipitation,
                            value = precipitation,
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponentView(
                        WeatherInfoComponent.AirPressure(
                            titleId = R.string.air_pressure,
                            value = airPressure,
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponentView(
                        WeatherInfoComponent.Humidity(
                            titleId = R.string.humidity,
                            value = humidity,
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WeatherInfoComponentView(
                        WeatherInfoComponent.WindSpeed(
                            titleId = R.string.wind_speed,
                            value = windSpeed,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.last_fetched, lastFetchedInfo),
                style = MaterialTheme.typography.caption,
                color = Color.Gray,
            )
        }
    }
}

@Composable
private fun BigTemperatureItem(
    temperatureValue: String,
) {
    Text(
        text = "$temperatureValue°",
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.ExtraBold,
    )
}

@Composable
private fun SmallTemperatureItem(
    temperatureValue: String,
) {
    Text(
        text = "$temperatureValue°",
        style = MaterialTheme.typography.h6,
    )
}
