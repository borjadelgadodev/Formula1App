package com.borjadelgadodev.formula1app.domain.mappers

import com.borjadelgadodev.formula1app.domain.models.Driver
import com.borjadelgadodev.formula1app.data.model.DriverDTO

fun DriverDTO.toDriver(): Driver {
    return Driver(
        driverNumber = driver_number ?: 0,
        fullName = full_name ?: "Unknown",
        teamName = team_name ?: "Unknown",
        countryCode = country_code ?: "Unknown",
        headshotUrl = headshot_url ?: "default_url"
    )
}