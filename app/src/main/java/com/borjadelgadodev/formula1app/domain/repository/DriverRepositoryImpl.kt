package com.borjadelgadodev.formula1app.domain.repository

import com.borjadelgadodev.formula1app.data.api.ApiService
import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.domain.mappers.toDriver

class DriverRepositoryImpl(private val apiService: ApiService) : DriverRepository {

    override suspend fun getDrivers(): List<Driver> {
        val response = apiService.getDrivers()
        return if (response.isSuccessful) {
            response.body()?.map { it.toDriver() } ?: emptyList()
        } else {
            emptyList()
        }
    }
}
