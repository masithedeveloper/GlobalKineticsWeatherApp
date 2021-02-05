package com.kinetics.weatherapp.openweatherapp.repository

import com.kinetics.weatherapp.openweatherapp.data.Resource
import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchWeather(): Flow<Resource<WeatherResponse>>
}