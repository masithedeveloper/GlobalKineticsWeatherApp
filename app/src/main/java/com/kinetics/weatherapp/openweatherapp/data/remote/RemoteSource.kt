package com.kinetics.weatherapp.openweatherapp.data.remote

import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse


interface RemoteSource {
    suspend fun fetchWeather(location: String): WeatherResponse
}