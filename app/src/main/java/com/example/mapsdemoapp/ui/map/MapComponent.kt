package com.example.mapsdemoapp.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mapsdemoapp.R
import com.example.mapsdemoapp.domain.location.models.Location
import com.example.mapsdemoapp.utils.extensions.toBitmap
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMapLongClickListener
import com.mapbox.maps.plugin.gestures.gestures

@Composable
fun MapComponent(
    mapStartingPoint: Point,
    currentMapStyle: String,
    onLongPress: (Point) -> Unit,
    savedLocations: List<Location>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }

    val mapLongClickListener: OnMapLongClickListener = remember {
        OnMapLongClickListener { point ->
            onLongPress(point)
            true
        }
    }

    val markerClickListener: OnPointAnnotationClickListener = remember {
        OnPointAnnotationClickListener {
            //TODO: Navigate to the next screen
            return@OnPointAnnotationClickListener true
        }
    }

    AndroidView(
        factory = {
            MapView(it).also { mapView ->
                mapView.getMapboxMap().apply {
                    loadStyleUri(Style.TRAFFIC_DAY)
                    flyTo(CameraOptions.Builder().zoom(9.0).center(mapStartingPoint).build())
                }
                pointAnnotationManager = mapView.annotations.createPointAnnotationManager().apply {
                    addClickListener(markerClickListener)
                }
                mapView.gestures.addOnMapLongClickListener(mapLongClickListener)
            }
        },
        update = { mapView ->
            mapView.getMapboxMap().apply {
                loadStyleUri(currentMapStyle)
            }
            savedLocations.forEach { location ->
                val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                    .withPoint(Point.fromLngLat(location.longitude, location.latitude))
                    .withIconImage(R.drawable.ic_marker_blue.toBitmap(context))
                pointAnnotationManager?.create(pointAnnotationOptions)
            }
        },
        onRelease = { mapView ->
            mapView.gestures.removeOnMapLongClickListener(mapLongClickListener)
            pointAnnotationManager?.removeClickListener(markerClickListener)
        },
        modifier = modifier,
    )
}
