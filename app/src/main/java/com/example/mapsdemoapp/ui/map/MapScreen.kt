package com.example.mapsdemoapp.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mapsdemoapp.R
import com.mapbox.geojson.Point
import com.mapbox.maps.Style

@Composable
fun MapScreen() {

    val startingPoint = remember { Point.fromLngLat(14.5, 46.0) }

    var currentMapStyle by remember {
        mutableStateOf(Style.OUTDOORS)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        MapComponent(
            point = startingPoint,
            currentMapStyle = currentMapStyle,
        )
        Button(
            onClick = {
                when (currentMapStyle) {
                    Style.OUTDOORS -> {
                        currentMapStyle = Style.SATELLITE_STREETS
                    }

                    Style.SATELLITE_STREETS -> {
                        currentMapStyle = Style.OUTDOORS
                    }
                }
            },
            modifier = Modifier.padding(bottom = 16.dp),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(stringResource(id = R.string.toggle_map_type))
        }
    }
}
