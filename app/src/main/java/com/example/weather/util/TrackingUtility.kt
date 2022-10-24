package com.example.weather.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weather.model.Coordinates

object TrackingUtility {

    fun getCoordinate(locationManager: LocationManager, context: Context): Coordinates? {

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED

        if(hasAccessCoarseLocationPermission || hasAccessFineLocationPermission) {
            return null
        }

        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ?:
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        return location?.let { data ->
            Coordinates(
                latitude = data.latitude,
                longitude = data.longitude
            )
        }
    }

}