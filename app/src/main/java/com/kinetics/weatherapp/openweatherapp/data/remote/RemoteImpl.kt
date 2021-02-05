package com.kinetics.weatherapp.openweatherapp.data.remote

import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import com.kinetics.weatherapp.openweatherapp.data.remote.api.ApiService
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val apiService: ApiService) : RemoteSource {

    override suspend fun fetchWeather(location: String): WeatherResponse {
        return apiService.getWeather(location)
    }
}