package com.kinetics.weatherapp.openweatherapp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.kinetics.weatherapp.openweatherapp.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import com.kinetics.weatherapp.openweatherapp.databinding.ActivityMainBinding;
import com.kinetics.weatherapp.openweatherapp.utils.LocationHandler;
import com.kinetics.weatherapp.openweatherapp.utils.Utilities;
import timber.log.Timber;

/**
 * @author masistoto
 */
public class MainActivity extends AppCompatActivity  {

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 98;

    @Inject
    LocationManager mLocationManager;

    private String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
        }
    };

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        checkLocationPermission();

        checkGpsEnabled();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
    }

    private void checkGpsEnabled() {
        if (Utilities.isLocationProviderEnabled(mLocationManager)) {
            Timber.d("gps enabled");
            startLocationUpdates();
        } else {
            Timber.d("gps disabled");
            Utilities.enableLocationProvider(this, getString(R.string.enable_gps),
                    getString(R.string.gps_enable_prompt));
        }
    }

    private void startLocationUpdates() {
        LocationHandler.getLocationHandler(this, mLocationCallback);
    }

    public void checkLocationPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Utilities.showDialog(this, getString(R.string.location_permission_dialog_title),
                        getString(R.string.location_permission_prompt),
                        (dialog, i) -> requestPermission(permissions),
                        (dialog, i) -> Utilities.showToast(this,
                                getString(R.string.set_custom_location),
                                Toast.LENGTH_LONG));
            } else {
                requestPermission(permissions);
            }
        } else {
            Timber.d("Permission granted");
            startLocationUpdates();
        }
    }

    private void requestPermission(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_ACCESS_COARSE_LOCATION);
    }

    private boolean isPermissionGranted(String permission) {
        return ActivityCompat.checkSelfPermission(this,
                permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] ==
                        PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                    Timber.d("permission granted");
                } else {
                    Timber.d("permission not granted");
                }
            }
        }
    }
}
