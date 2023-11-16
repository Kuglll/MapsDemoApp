package com.example.mapsdemoapp.ui.forecast

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherInfoComponentView(
    compontent: WeatherInfoComponent
) {
    Text(
        text = stringResource(id = compontent.titleId),
        style = MaterialTheme.typography.body2,
        color = Color.Gray,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = compontent.text,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.SemiBold,
    )
}

sealed class WeatherInfoComponent(
    @StringRes open val titleId: Int,
) {
    abstract val text: String
        @Composable get

    data class Precipitation(
        @StringRes override val titleId: Int,
        val value: String?,
    ) : WeatherInfoComponent(
        titleId = titleId,
    ) {
        override val text: String
            @Composable get() = if (value.isNullOrEmpty()){
                "-"
            } else {
                "$value mm/h"
            }
    }

    data class AirPressure(
        @StringRes override val titleId: Int,
        val value: String,
    ) : WeatherInfoComponent(
        titleId = titleId,
    ) {
        override val text: String
            @Composable get() = "$value hPa"
    }

    data class Humidity(
        @StringRes override val titleId: Int,
        val value: String,
    ) : WeatherInfoComponent(
        titleId = titleId,
    ) {
        override val text: String
            @Composable get() = "$value%"
    }

    data class WindSpeed(
        @StringRes override val titleId: Int,
        val value: String,
    ) : WeatherInfoComponent(
        titleId = titleId,
    ) {
        override val text: String
            @Composable get() = "$value m/s"
    }

}

//TODO: Add previews
