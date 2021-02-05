package com.kinetics.weatherapp.openweatherapp.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import timber.log.Timber;

public class LocationProviderImpl extends PreferenceProvider implements LocationProvider, OnSuccessListener<Location> {

    private static final String USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION";

    private static final String CUSTOM_LOCATION = "CUSTOM_LOCATION";

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location deviceWeatherLocation;

    @Inject
    LocationProviderImpl(Context context, FusedLocationProviderClient client) {
        super(context);
        mFusedLocationProviderClient = client;
    }

    @Override
    public boolean isLocationChanged(String location) {
        Timber.d("Device location change %b", hasDeviceLocationChanged(deviceWeatherLocation));
        return hasDeviceLocationChanged(deviceWeatherLocation) || hasCustomLocationChanged(location);
    }

    @NotNull
    @Override
    public String getPreferredLocationString() {
        if (isUsingDeviceLocation()) {
            if (getLastDeviceLocation() == null) {
                return getCustomLocationName();
            } else {
                String latitude = String.valueOf(getLastDeviceLocation().getLatitude());
                String longitude = String.valueOf(getLastDeviceLocation().getLongitude());
                Timber.d("Coordinates %s,%s", latitude, longitude);
                return (latitude + "," + longitude);
            }
        } else {
            return getCustomLocationName();
        }
    }

    private boolean hasDeviceLocationChanged(Location weatherLocation) {
        if (!isUsingDeviceLocation()) {
            return false;
        }

        double comparisonThreshold = 0.03;

        if (getLastDeviceLocation() != null && weatherLocation != null) {
            return Math.abs(getLastDeviceLocation().getLatitude() - weatherLocation.getLatitude()) > comparisonThreshold
                    && Math.abs(getLastDeviceLocation().getLongitude() - weatherLocation.getLongitude()) > comparisonThreshold;

        }
        return false;
    }

    private boolean hasCustomLocationChanged(String location) {
        if (!isUsingDeviceLocation()) {
            String customLocationName = getCustomLocationName();
            return !customLocationName.equals(location);
        }
        return false;
    }

    private boolean isUsingDeviceLocation() {
        startLocationUpdates();
        return getSharedPreferences().getBoolean(USE_DEVICE_LOCATION, true);
    }

    private String getCustomLocationName() {
        return getSharedPreferences().getString(CUSTOM_LOCATION, null);
    }

    private Location getLastDeviceLocation() {

        startLocationUpdates();

        return deviceWeatherLocation;
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this);

        new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location weatherLocation : locationResult.getLocations()) {
                    if (weatherLocation != null) {
                        deviceWeatherLocation = weatherLocation;
                    }
                }

            }
        };
    }

    @Override
    public void onSuccess(Location weatherLocation) {
        if (weatherLocation != null) {
            deviceWeatherLocation = weatherLocation;
        } else {
            Timber.d("Device Location not yet available. Please try again");
        }
    }

}
