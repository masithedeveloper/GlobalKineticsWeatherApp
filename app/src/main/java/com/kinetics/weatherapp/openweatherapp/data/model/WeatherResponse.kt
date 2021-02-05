package com.kinetics.weatherapp.openweatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "weather_response")
data class WeatherResponse (
        @PrimaryKey
        val id: Long,
        val coord: Coord,
        val weather: List<Weather>,
        val base: String,
        val main: Main,
        val visibility: Long,
        val wind: Wind,
        val clouds: Clouds,
        val dt: Long,
        val sys: Sys,
        val timezone: Long,
        val name: String,
        val cod: Long
)

@JsonClass(generateAdapter = true)
data class Clouds (
        val all: Long
)

@JsonClass(generateAdapter = true)
data class Coord (
        val lon: Double,
        val lat: Double
)

@JsonClass(generateAdapter = true)
data class Main (
        val temp: Double?,
        val feelsLike: Double?,
        val tempMin: Double?,
        val tempMax: Double?,
        val pressure: Long?,
        val humidity: Long?
)

@JsonClass(generateAdapter = true)
data class Sys (
        val type: Long,
        val id: Long,
        val country: String,
        val sunrise: Long,
        val sunset: Long
)

@JsonClass(generateAdapter = true)
data class Weather (
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
)

@JsonClass(generateAdapter = true)
data class Wind (
        val speed: Double,
        val deg: Long
)