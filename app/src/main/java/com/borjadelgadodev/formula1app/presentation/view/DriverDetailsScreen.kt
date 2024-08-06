package com.borjadelgadodev.formula1app.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.borjadelgadodev.formula1app.domain.models.Driver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDetailsScreen(driverId: String?, navController: NavController) {
    // Aquí deberías obtener los detalles del piloto utilizando el ID.
    // Por ahora se simula con datos ficticios.
    val driver = Driver(
        driverNumber = driverId?.toInt() ?: 0,
        fullName = "Driver $driverId",
        teamName = "Team X",
        countryCode = "XX",
        headshotUrl = "https://via.placeholder.com/150"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Driver Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)) {
                Text(text = driver.fullName, style = MaterialTheme.typography.titleLarge)
                Text(text = "Team: ${driver.teamName}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Country: ${driver.countryCode}", style = MaterialTheme.typography.bodyLarge)
                // Aquí puedes añadir más detalles si es necesario
            }
        }
    )
}
