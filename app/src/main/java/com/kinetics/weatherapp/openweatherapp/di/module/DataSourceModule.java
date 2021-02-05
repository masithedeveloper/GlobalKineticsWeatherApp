package com.kinetics.weatherapp.openweatherapp.di.module;

import dagger.Binds;
import dagger.Module;
import com.kinetics.weatherapp.openweatherapp.data.local.LocalDataSource;
import com.kinetics.weatherapp.openweatherapp.data.local.LocalDataSourceImpl;
import com.kinetics.weatherapp.openweatherapp.data.remote.RemoteImpl;
import com.kinetics.weatherapp.openweatherapp.data.remote.RemoteSource;
import com.kinetics.weatherapp.openweatherapp.repository.Repository;
import com.kinetics.weatherapp.openweatherapp.repository.WeatherRepository;

@Module
public abstract class DataSourceModule {

    @Binds
    abstract LocalDataSource provideDataSource(LocalDataSourceImpl localDataSource);

    @Binds
    abstract Repository provideRepoImpl(WeatherRepository repo);

    @Binds
    abstract RemoteSource provideRemoteImpl(RemoteImpl remote);
}

