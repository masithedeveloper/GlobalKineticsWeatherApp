package com.kinetics.weatherapp.openweatherapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import com.kinetics.weatherapp.openweatherapp.ui.WeatherViewModel;
import com.kinetics.weatherapp.openweatherapp.viewmodel.WeatherViewModelFactory;

/**
 * @author masistoto
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel currentWeatherViewModel(WeatherViewModel weatherViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(WeatherViewModelFactory factory);
}
