package com.borjadelgadodev.formula1app.domain.usecases

import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.domain.repository.DriverRepository

class GetDriversUseCase(private val driverRepository: DriverRepository) {

    suspend fun execute(): List<Driver> {
        return driverRepository.getDrivers()
    }
}
