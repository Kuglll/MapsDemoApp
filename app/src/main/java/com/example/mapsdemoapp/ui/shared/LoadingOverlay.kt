package com.example.mapsdemoapp.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun LoadingOverlay(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Black.copy(alpha = 0.8f),
        modifier = modifier
            .fillMaxSize()
            .zIndex(1f),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
//                CircularProgressIndicator(
//                    modifier = Modifier.size(78.dp),
//                    color = dark_gray,
//                    strokeWidth = 2.dp,
//                    progress = 1f,
//                )
                CircularProgressIndicator(
                    modifier = Modifier.size(78.dp),
                    color = Color.Blue,
                    strokeWidth = 2.dp,
                )
            }
        }
    }
}

@Preview("Loading overlay")
@Composable
fun PreviewLoadingOverlay() {
    LoadingOverlay()
}
