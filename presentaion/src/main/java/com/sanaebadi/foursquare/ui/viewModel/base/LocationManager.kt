package com.sanaebadi.foursquare.ui.viewModel.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.sanaebadi.foursquare.R
import com.sanaebadi.foursquare.preferences.PreferencesHelper
import com.sanaebadi.foursquare.util.*
import javax.inject.Inject

class LocationManager @Inject constructor(
    private val callback: OnLocationCallback,
    private val preferencesHelper: PreferencesHelper,
    private val activity: AppCompatActivity,
    private val lifecycle: Lifecycle
) : LifecycleObserver {

    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationSettingsRequest: LocationSettingsRequest
    private lateinit var settingsClient: SettingsClient
    private lateinit var locationRequest: LocationRequest
    private var timeoutHandler: Handler = Handler()
    private var showRational: Boolean = false
    private var forceDetectLocation: Boolean = false
    private var locationIsOn: Boolean = false
    private var enabled = false


    init {
        lifecycle.addObserver(this)
    }

    fun enable(
        forceDetectLocation: Boolean = true,
        showRational: Boolean = false
    ) {

        this.showRational = showRational
        this.forceDetectLocation = forceDetectLocation

        val manager =
            activity.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager

        locationIsOn =
            activity.checkAppPermission(FINE_LOCATION) &&
                    (manager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) ||
                            manager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER))

        enabled = true

        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && (locationIsOn || forceDetectLocation))
            start()
    }

    fun disable() {
        stop()
        enabled = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        settingsClient = LocationServices.getSettingsClient(activity)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        locationRequest = LocationRequest().apply {
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = LOCATION_REQUEST_FASTEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)
                callback.onNewLocation(result?.lastLocation)
                preferencesHelper.setUserLocationChanged()
                stopLocationUpdates()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enabled && (locationIsOn || forceDetectLocation)) {
            detectLocation()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        if (enabled) {
            stopLocationUpdates()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        lifecycle.removeObserver(this)
    }

    private fun detectLocation() {
        if (activity.checkAppPermission(FINE_LOCATION))
            startLocationUpdates()
        else
            requestPermissions()
    }

    private fun requestPermissions() {
        when {
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                FINE_LOCATION
            ) -> {
                showDialog()
            }
            else -> {
                activity.requestPermission(Constants.LOCATION_PERMISSION_RUNTIME, FINE_LOCATION)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener {
                callback.onStartLocating()
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
                timeoutHandler.postDelayed(noLocationRunnable, LOCATION_REQUEST_TIMEOUT)
            }
            .addOnFailureListener {
                when ((it as ApiException).statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        (it as ResolvableApiException).startResolutionForResult(
                            activity,
                            Constants.CHECK_PHONE_SETTING
                        )
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        activity.appToast(activity.getString(R.string.inadequate_location_settings))
                    }
                }
            }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
            .addOnCompleteListener {

            }
        timeoutHandler.removeCallbacks(noLocationRunnable)
    }

    private fun showDialog() {
        AlertDialog.Builder(activity)
            .setMessage(R.string.needs_location_permission_for_auto_detect)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                disable()
                dialog.dismiss()
            }
            .setPositiveButton(R.string.btn_show_permission) { dialog, _ ->
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        FINE_LOCATION
                    ) || showRational
                ) {
                    activity.requestPermission(Constants.LOCATION_PERMISSION_RUNTIME, FINE_LOCATION)
                } else {
                    activity.openAppPermissionSetting()
                }
                dialog.dismiss()
            }
            .show()
    }

    @SuppressLint("MissingPermission")
    private val noLocationRunnable: Runnable = Runnable {
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                callback.onNewLocation(it)
                stopLocationUpdates()
            }
            .addOnFailureListener {
                callback.onNewLocation(null)
                stopLocationUpdates()
            }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int) {
        when (requestCode) {
            Constants.CHECK_PHONE_SETTING -> {
                if (resultCode == AppCompatActivity.RESULT_OK)
                    detectLocation()
                else if (resultCode == AppCompatActivity.RESULT_CANCELED)
                    disable()
            }
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == Constants.LOCATION_PERMISSION_RUNTIME &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            startLocationUpdates()
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, FINE_LOCATION)) {
            disable()
        } else {
            disable()
            if (shouldShowAppSettingDialog) {
                activity.showAppSettingDialog(
                    FINE_LOCATION,
                    Constants.LOCATION_PERMISSION_RUNTIME,
                    R.string.need_app_location_permissions_setting,
                    ::disable
                )
            }
            shouldShowAppSettingDialog = true
        }
    }

    companion object {
        var shouldShowAppSettingDialog = false
        const val LOCATION_REQUEST_TIMEOUT: Long = 30000
        const val LOCATION_REQUEST_INTERVAL: Long = 10000
        const val LOCATION_REQUEST_FASTEST_INTERVAL: Long = LOCATION_REQUEST_INTERVAL / 2
        const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    }

}



