package com.example.mapsdemoapp.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.ui.shared.base.BaseComposable
import com.mapbox.geojson.Point

@Composable
fun MapScreen(
    viewModel: MapViewModel = viewModel(),
) {
    val startingPoint = remember { Point.fromLngLat(14.5, 46.0) }

    BaseComposable(viewModel = viewModel) { mapState ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            MapComponent(
                mapStartingPoint = startingPoint,
                currentMapStyle = mapState.currentMapStyle,
                onLongPress = viewModel::onLongPress,
                savedLocations = mapState.savedLocations,
            )
            Button(
                onClick = viewModel::onToggleMapTypeClicked,
                modifier = Modifier.padding(bottom = 16.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(stringResource(id = R.string.toggle_map_type))
            }
        }
    }

}
