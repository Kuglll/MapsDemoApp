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
import com.example.mapsdemoapp.utils.extensions.toBitmap
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMapLongClickListener
import com.mapbox.maps.plugin.gestures.gestures

@Composable
fun MapComponent(
    modifier: Modifier = Modifier,
    point: Point?,
) {
    val context = LocalContext.current
    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }

    val mapLongClickListener: OnMapLongClickListener = remember {
        OnMapLongClickListener { point ->
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(point.longitude(), point.latitude()))
                .withIconImage(R.drawable.ic_marker_blue.toBitmap(context))
            pointAnnotationManager?.create(pointAnnotationOptions)
            //TODO: Store to DB
            true
        }
    }

    AndroidView(
        factory = {
            MapView(it).also { mapView ->
                mapView.getMapboxMap().apply {
                    loadStyleUri(Style.TRAFFIC_DAY)
                    flyTo(CameraOptions.Builder().zoom(9.0).center(point).build())
                }
                pointAnnotationManager = mapView.annotations.createPointAnnotationManager()
                mapView.gestures.addOnMapLongClickListener(mapLongClickListener)
            }
        },
        onRelease = { mapView ->
            mapView.gestures.removeOnMapLongClickListener(mapLongClickListener)
        },
        modifier = modifier,
    )
}
