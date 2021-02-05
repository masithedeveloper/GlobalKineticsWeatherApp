package com.kinetics.weatherapp.openweatherapp.data.local.db

import androidx.room.TypeConverter
import com.kinetics.weatherapp.openweatherapp.data.model.*
import com.squareup.moshi.Moshi.Builder
import com.squareup.moshi.Types
import java.io.IOException

/**
 * @author masistoto
 */
class WeatherTypeConverter {
    private val moshi = Builder().build()

    @TypeConverter
    fun weatherResponseToJson(weather: WeatherResponse?): String? {
        return if (weather == null) null else moshi.adapter<Any>(WeatherResponse::class.java).toJson(weather)
    }

    @TypeConverter
    fun weatherResponseFromJson(string: String): WeatherResponse? {
        val weather: WeatherResponse?
        weather = try {
            moshi.adapter<WeatherResponse>(WeatherResponse::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return weather
    }

    @TypeConverter
    fun locationToJson(weatherLocation: Coord?): String? {
        return if (weatherLocation == null) null else moshi.adapter<Any>(Coord::class.java).toJson(weatherLocation)
    }

    @TypeConverter
    fun locationFromJson(string: String?): Coord? {
        val location: Coord?
        location = try {
            moshi.adapter<Coord>(Coord::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return location
    }

    @TypeConverter
    fun weatherToJson(weather: List<Weather>?): String? {
        return if (weather == null) null else moshi.adapter<Any>(Weather::class.java).toJson(weather)
    }

    @TypeConverter
    fun weatherFromJson(string: String): List<Weather>? {
        val weather: List<Weather>?
        weather = try {
            moshi.adapter<List<Weather>>(Types.newParameterizedType(List::class.java, String::class.java)).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return weather
    }

    @TypeConverter
    fun mainToJson(main: Main?): String? {
        return if (main == null) null else moshi.adapter<Any>(Main::class.java).toJson(main)
    }

    @TypeConverter
    fun mainFromJson(string: String): Main? {
        val main: Main?
        main = try {
            moshi.adapter<Main>(Main::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return main
    }

    @TypeConverter
    fun windToJson(wind: Wind?): String? {
        return if (wind == null) null else moshi.adapter<Any>(Wind::class.java).toJson(wind)
    }

    @TypeConverter
    fun windFromJson(string: String): Wind? {
        val wind: Wind?
        wind = try {
            moshi.adapter<Wind>(Wind::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return wind
    }

    @TypeConverter
    fun cloudsToJson(clouds: Clouds?): String? {
        return if (clouds == null) null else moshi.adapter<Any>(Clouds::class.java).toJson(clouds)
    }

    @TypeConverter
    fun cloudsFromJson(string: String): Clouds? {
        val clouds: Clouds?
        clouds = try {
            moshi.adapter<Clouds>(Clouds::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return clouds
    }

    @TypeConverter
    fun sysToJson(sys: Sys?): String? {
        return if (sys == null) null else moshi.adapter<Any>(Sys::class.java).toJson(sys)
    }

    @TypeConverter
    fun sysFromJson(string: String): Sys? {
        val sys: Sys?
        sys = try {
            moshi.adapter<Sys>(Sys::class.java).fromJson(string)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return sys
    }
}