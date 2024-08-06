package com.borjadelgadodev.formula1app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.borjadelgadodev.formula1app.presentation.view.ContactScreen
import com.borjadelgadodev.formula1app.presentation.view.DriverDetailsScreen
import com.borjadelgadodev.formula1app.presentation.view.DriverScreen
import com.borjadelgadodev.formula1app.presentation.view.SplashScreen
import com.borjadelgadodev.formula1app.presentation.viewmodel.DriverViewModel

@Composable
fun NavManager(
    navController: NavHostController,
    viewModel: DriverViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen",
        modifier = modifier
    ) {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("driver_screen") {
            DriverScreen(navController, viewModel)
        }
        composable("driver_details_screen/{driverId}") { backStackEntry ->
            val driverId = backStackEntry.arguments?.getString("driverId")
            DriverDetailsScreen(driverId, navController)
        }
        composable("contact_screen") {
            ContactScreen()
        }
    }
}