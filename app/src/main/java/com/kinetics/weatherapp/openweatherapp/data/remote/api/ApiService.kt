package com.kinetics.weatherapp.openweatherapp.data.remote.api

import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeather(@Query("q") location : String): WeatherResponse
}