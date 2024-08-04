package com.borjadelgadodev.formula1app.data.api

import com.borjadelgadodev.formula1app.domain.models.CarData
import com.borjadelgadodev.formula1app.data.model.DriverDTO
import com.borjadelgadodev.formula1app.utils.Constants.Companion.CAR_DATA_ENDPOINT
import com.borjadelgadodev.formula1app.utils.Constants.Companion.DRIVERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(DRIVERS_ENDPOINT)
    suspend fun getDrivers(): Response<List<DriverDTO>>

    @GET(CAR_DATA_ENDPOINT)
    suspend fun getCarData(): Response<List<CarData>>
}