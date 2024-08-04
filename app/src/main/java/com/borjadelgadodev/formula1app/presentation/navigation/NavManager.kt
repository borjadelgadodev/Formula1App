package com.borjadelgadodev.formula1app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.borjadelgadodev.formula1app.presentation.viewmodel.DriverViewModel

@Composable
fun NavManager(viewModel: DriverViewModel){

    val navController = rememberNavController()
}