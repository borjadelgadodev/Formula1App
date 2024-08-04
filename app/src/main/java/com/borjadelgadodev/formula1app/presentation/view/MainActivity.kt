package com.borjadelgadodev.formula1app.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import coil.compose.rememberImagePainter
import com.borjadelgadodev.formula1app.presentation.viewmodel.DriverViewModel
import com.borjadelgadodev.formula1app.data.api.RetrofitInstance
import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.domain.repository.DriverRepository
import com.borjadelgadodev.formula1app.domain.repository.DriverRepositoryImpl
import com.borjadelgadodev.formula1app.domain.usecases.GetDriversUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Initialize dependencies
            val apiService = RetrofitInstance.api
            val driverRepository: DriverRepository = DriverRepositoryImpl(apiService)
            val getDriversUseCase = GetDriversUseCase(driverRepository)
            val viewModel = DriverViewModel(getDriversUseCase)

            // Main screen
            DriverScreen(viewModel)
        }
    }
}

@Composable
fun DriverScreen(viewModel: DriverViewModel) {
    val drivers by viewModel.drivers.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("F1 Drivers") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                errorMessage?.let {
                    Text(text = it, style = MaterialTheme.typography.h6)
                }

                if (drivers.isEmpty()) {
                    Text(text = "Loading...", style = MaterialTheme.typography.h6)
                } else {
                    LazyColumn {
                        items(drivers) { driver ->
                            DriverItem(driver)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DriverItem(driver: Driver?) {
    if (driver == null) {
        Text("No data available")
        return
    }

    Row(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = driver.fullName, style = MaterialTheme.typography.h6)
            Text(text = driver.teamName, style = MaterialTheme.typography.body2)
            Text(text = driver.countryCode, style = MaterialTheme.typography.body2)
            Text(text = "Driver Number: ${driver.driverNumber}", style = MaterialTheme.typography.body2)
        }

        Spacer(modifier = Modifier.width(16.dp))

        val imagePainter = rememberImagePainter(driver.headshotUrl) // Use Coil to load image
        Image(
            painter = imagePainter,
            contentDescription = "Headshot of ${driver.fullName}",
            modifier = Modifier
                .size(80.dp) // Adjust the size as needed
                .padding(start = 8.dp)
        )
    }
}




