package com.kinetics.weatherapp.openweatherapp.data.local

import com.kinetics.weatherapp.openweatherapp.data.local.db.WeatherDao
import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse
import com.kinetics.weatherapp.openweatherapp.provider.LocationProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val weatherDao: WeatherDao, private val locationProvider: LocationProvider)
    : LocalDataSource {

    override suspend fun save(weatherResponse: WeatherResponse?) {
        weatherDao.insert(weatherResponse)
    }

    override fun hasLocationChanged(weatherResponse: WeatherResponse): Boolean {
        return locationProvider.isLocationChanged(weatherResponse.name);
    }

    override fun getWeather(): Flow<WeatherResponse> {
        return weatherDao.fetchWeather()
    }
}