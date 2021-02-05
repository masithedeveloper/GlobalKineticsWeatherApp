package com.kinetics.weatherapp.openweatherapp.di.module;

import dagger.Binds;
import dagger.Module;
import com.kinetics.weatherapp.openweatherapp.provider.UnitProvider;
import com.kinetics.weatherapp.openweatherapp.provider.UnitProviderImpl;

/**
 * @author masistoto
 */
@Module
public abstract class UnitModule {

    @Binds
    abstract UnitProvider provideUnitProvider(UnitProviderImpl unitProvider);
}
