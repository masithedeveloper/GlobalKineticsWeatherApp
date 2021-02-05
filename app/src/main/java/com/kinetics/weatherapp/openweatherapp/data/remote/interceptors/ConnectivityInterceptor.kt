package com.kinetics.weatherapp.openweatherapp.data.remote.interceptors

import android.content.Context
import com.kinetics.weatherapp.openweatherapp.utils.Utilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utilities.isOnline(context)) {
            throw IOException("Please check your internet connection")
        }
        return chain.proceed(chain.request())
    }
}