package com.borjadelgadodev.formula1app.domain.repository

import com.borjadelgadodev.formula1app.domain.models.Driver

interface DriverRepository {
    suspend fun getDrivers(): List<Driver>
}
