package com.borjadelgadodev.formula1app.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.presentation.viewmodel.DriverViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverScreen(navController: NavController, viewModel: DriverViewModel) {
    val drivers by viewModel.drivers.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("F1 Drivers") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                errorMessage?.let {
                    ErrorMessage(it)
                }

                if (drivers.isEmpty()) {
                    LoadingMessage()
                } else {
                    DriverList(drivers, navController)
                }
            }
        }
    )
}

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun LoadingMessage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun DriverList(drivers: List<Driver>, navController: NavController) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(drivers) { driver ->
            DriverItem(driver, navController)
        }
    }
}

@Composable
fun DriverItem(driver: Driver, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("driver_details_screen/${driver.driverNumber}") }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = driver.fullName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black // Asegurarse de que el color del texto contraste con el fondo
            )
            Text(
                text = driver.teamName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black // Asegurarse de que el color del texto contraste con el fondo
            )
            Text(
                text = driver.countryCode,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black // Asegurarse de que el color del texto contraste con el fondo
            )
            Text(
                text = "Driver Number: ${driver.driverNumber}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black // Asegurarse de que el color del texto contraste con el fondo
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        val imagePainter = rememberImagePainter(driver.headshotUrl)
        Image(
            painter = imagePainter,
            contentDescription = "Headshot of ${driver.fullName}",
            modifier = Modifier
                .size(80.dp)
                .padding(start = 8.dp)
        )
    }
}
