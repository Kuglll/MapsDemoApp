package com.example.mapsdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.mapsdemoapp.ui.map.MapComponent
import com.mapbox.geojson.Point

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startingPoint = Point.fromLngLat(14.5, 46.0)

        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                MapComponent(point = startingPoint)
            }
        }
    }
}
