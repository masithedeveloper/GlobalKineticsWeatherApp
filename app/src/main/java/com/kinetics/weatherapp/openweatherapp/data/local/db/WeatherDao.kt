package com.kinetics.weatherapp.openweatherapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("select * from weather_response")
    fun fetchWeather(): Flow<WeatherResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherResponse?)
}
