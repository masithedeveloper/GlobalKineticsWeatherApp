package com.kinetics.weatherapp.openweatherapp.di.module;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import com.kinetics.weatherapp.openweatherapp.provider.LocationProvider;
import com.kinetics.weatherapp.openweatherapp.provider.LocationProviderImpl;

/**
 * @author masistoto
 */

@Module
public abstract class LocationModule {

    @Singleton
    @Binds
    abstract LocationProvider provideLocationProvider(LocationProviderImpl locationProvider);

}
