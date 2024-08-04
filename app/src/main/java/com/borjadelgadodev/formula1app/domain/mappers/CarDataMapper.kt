package com.borjadelgadodev.formula1app.domain.mappers

import com.borjadelgadodev.formula1app.data.model.CarDataDTO
import com.borjadelgadodev.formula1app.domain.models.CarData

fun CarDataDTO.toCarData(): CarData {
    return CarData(
        driverNumber = driver_number,
        speed = speed
    )
}