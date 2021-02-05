package com.kinetics.weatherapp.openweatherapp.di

import android.app.Application
import com.kinetics.weatherapp.openweatherapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.kinetics.weatherapp.openweatherapp.WeatherApplication
import javax.inject.Singleton

/**
 * @author masistoto
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ApiModule::class, DatabaseModule::class,
    AppModule::class, UnitModule::class,
    LocationModule::class,
    DataSourceModule::class,
    ViewModelModule::class])

interface AppComponent {
    fun inject(application: WeatherApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}