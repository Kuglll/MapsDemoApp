package com.example.mapsdemoapp.ui.shared

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsdemoapp.ui.forecast.ForecastScreen
import com.example.mapsdemoapp.ui.map.MapScreen

sealed class Screen(
    val route: String,
){
    object MapScreen : Screen("map_screen")
    object ForecastScreen : Screen("forecast_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MapScreen.route){

        composable(route = Screen.MapScreen.route){
            MapScreen(
                onMarkerClicked = {
                    navController.navigate(Screen.ForecastScreen.route)
                },
            )
        }

        composable(route = Screen.ForecastScreen.route){
            ForecastScreen(
                onBackButtonClicked = {
                    navController.popBackStack()
                },
            )
        }

    }
}
