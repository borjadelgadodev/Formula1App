package com.borjadelgadodev.formula1app.data.model

data class CarDataDTO(
    val brake: Int,
    val date: String,
    val driver_number: Int,
    val drs: Int,
    val meeting_key: Int,
    val n_gear: Int,
    val rpm: Int,
    val session_key: Int,
    val speed: Int,
    val throttle: Int
)