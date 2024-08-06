package com.borjadelgadodev.formula1app.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.borjadelgadodev.formula1app.data.api.RetrofitInstance
import com.borjadelgadodev.formula1app.domain.repository.DriverRepository
import com.borjadelgadodev.formula1app.domain.repository.DriverRepositoryImpl
import com.borjadelgadodev.formula1app.domain.usecases.GetDriversUseCase
import com.borjadelgadodev.formula1app.presentation.navigation.NavManager
import com.borjadelgadodev.formula1app.presentation.viewmodel.DriverViewModel
import com.borjadelgadodev.formula1app.ui.theme.F1Red
import com.borjadelgadodev.formula1app.ui.theme.Formula1AppTheme
import androidx.compose.ui.res.painterResource
import com.borjadelgadodev.formula1app.R
import androidx.compose.foundation.layout.*
import com.borjadelgadodev.formula1app.ui.theme.F1GreyDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Formula1App {
                val viewModel = initializeViewModel()
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { paddingValues ->
                    NavManager(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }

    private fun initializeViewModel(): DriverViewModel {
        val apiService = RetrofitInstance.api
        val driverRepository: DriverRepository = DriverRepositoryImpl(apiService)
        val getDriversUseCase = GetDriversUseCase(driverRepository)
        return DriverViewModel(getDriversUseCase)
    }
}

@Composable
fun Formula1App(content: @Composable () -> Unit) {
    Formula1AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Drivers", "driver_screen", R.drawable.casco),
        BottomNavItem("Contact", "contact_screen", R.drawable.conductor)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != "splash_screen") {
        BottomNavigation(
            backgroundColor = F1GreyDark,
            contentColor = Color.White,
            elevation = 8.dp
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (currentRoute == item.route) Color.White else F1Red)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            item.title,
                            color = if (currentRoute == item.route) Color.White else Color.LightGray
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray,
                    alwaysShowLabel = true
                )
            }
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int
)