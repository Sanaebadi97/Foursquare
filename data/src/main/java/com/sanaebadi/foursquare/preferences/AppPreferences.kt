package com.sanaebadi.foursquare.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PreferencesHelper {

    override fun setUserLocationChanged() {
        sharedPreferences.edit { putBoolean(LOCATION_CHANGED, true) }
    }

    override fun getUserLocationChanged(): Boolean {
        return sharedPreferences.getBoolean(LOCATION_CHANGED, false)
    }

    companion object {
        const val LOCATION_CHANGED = "LOCATION_CHANGED_SHOWN"
    }
}