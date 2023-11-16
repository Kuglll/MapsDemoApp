package com.example.mapsdemoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable

@Composable
fun MapsTheme(
    colors: Colors = MapsColors,
    shapes: Shapes = mapsShapes,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colors,
        shapes = shapes,
    ) {
        content()
    }
}
