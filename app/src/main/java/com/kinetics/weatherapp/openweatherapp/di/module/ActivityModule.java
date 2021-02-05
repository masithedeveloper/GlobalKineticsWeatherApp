package com.kinetics.weatherapp.openweatherapp.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.kinetics.weatherapp.openweatherapp.ui.activity.MainActivity;
import com.kinetics.weatherapp.openweatherapp.ui.fragment.WeatherFragment;

/**
 * @author masistoto
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract WeatherFragment contributeWeatherFragment();
}
