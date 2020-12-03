package com.sanaebadi.foursquare.ui.viewModel.base

import android.location.Location

interface OnLocationCallback {
    fun onNewLocation(location: Location?)
    fun onStartLocating()
}