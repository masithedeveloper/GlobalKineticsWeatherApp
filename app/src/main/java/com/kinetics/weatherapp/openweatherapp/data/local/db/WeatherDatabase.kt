package com.kinetics.weatherapp.openweatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kinetics.weatherapp.openweatherapp.data.model.WeatherResponse


@Database(entities = [WeatherResponse::class], version = 1, exportSchema = false)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao?
}
