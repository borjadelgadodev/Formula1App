package com.borjadelgadodev.formula1app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.domain.usecases.GetDriversUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DriverViewModel(private val getDriversUseCase: GetDriversUseCase) : ViewModel() {

    private val _drivers = MutableStateFlow<List<Driver>>(emptyList())
    val drivers: StateFlow<List<Driver>> = _drivers

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchDrivers()
    }

    private fun fetchDrivers() {
        viewModelScope.launch {
            try {
                val fetchedDrivers = getDriversUseCase.execute()
                val uniqueDriversByNumber = fetchedDrivers.distinctBy { it.driverNumber }
                val uniqueDrivers = getUniqueDrivers(uniqueDriversByNumber)
                val filteredDrivers = filterDriversByTeam(uniqueDrivers)
                _drivers.value = filteredDrivers
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    private fun getUniqueDrivers(drivers: List<Driver>): List<Driver> {
        val seenNames = mutableSetOf<String>()
        return drivers.filter { driver ->
            val isUnique = seenNames.add(driver.fullName)
            isUnique
        }
    }

    private fun filterDriversByTeam(drivers: List<Driver>): List<Driver> {
        return drivers.filter { driver ->
            !driver.teamName.isNullOrBlank()
        }
    }
}
