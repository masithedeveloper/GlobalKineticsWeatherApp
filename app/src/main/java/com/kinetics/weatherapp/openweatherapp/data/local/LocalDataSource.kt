package com.kinetics.weatherapp.openweatherapp.data.local

import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun save(weatherResponse: WeatherResponse?)
    fun hasLocationChanged(weatherResponse: WeatherResponse): Boolean
    fun getWeather(): Flow<WeatherResponse>
}
