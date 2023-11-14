package com.example.mapsdemoapp.ui.shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mapsdemoapp.ui.forecast.ForecastScreen
import com.example.mapsdemoapp.ui.map.MapScreen
import com.example.mapsdemoapp.ui.shared.NavigationParameter.LOCATION_ID_PARAMETER

object NavigationParameter {
    const val LOCATION_ID_PARAMETER = "locationId"
}

sealed class Screen(
    val route: String,
) {
    object MapScreen : Screen("map_screen")
    object ForecastScreen : Screen("forecast_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MapScreen.route) {

        composable(route = Screen.MapScreen.route) {
            MapScreen(
                onNavigateToForecast = { locationId ->
                    navController.navigate("${Screen.ForecastScreen.route}/$locationId")
                },
            )
        }

        composable(
            route = "${Screen.ForecastScreen.route}/{$LOCATION_ID_PARAMETER}",
            arguments = listOf(
                navArgument(LOCATION_ID_PARAMETER){
                    type = NavType.IntType
                }
            )
        ) {
            ForecastScreen(
                onBackButtonClicked = {
                    navController.popBackStack()
                },
            )
        }

    }
}
