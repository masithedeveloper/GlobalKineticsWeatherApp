package com.kinetics.weatherapp.openweatherapp.provider;

import org.jetbrains.annotations.NotNull;

public interface LocationProvider {

    boolean isLocationChanged(String location);

    @NotNull  String getPreferredLocationString();
}
