package com.borjadelgadodev.formula1app.domain.models

data class Driver(
    val driverNumber: Int,
    val fullName: String,
    val teamName: String,
    val countryCode: String,
    val headshotUrl: String
)
