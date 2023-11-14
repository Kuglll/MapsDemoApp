package com.example.mapsdemoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.example.mapsdemoapp.ui.shared.Navigation
import com.example.mapsdemoapp.ui.theme.Colors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colors = Colors,
            ){
                Navigation()
            }
        }
    }
}
