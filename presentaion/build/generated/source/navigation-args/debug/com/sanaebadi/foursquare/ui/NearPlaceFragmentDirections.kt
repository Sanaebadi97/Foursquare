package com.sanaebadi.foursquare.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.sanaebadi.foursquare.R

class NearPlaceFragmentDirections private constructor() {
  companion object {
    fun actionNearPlaceFragmentToPlaceDetailsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_nearPlaceFragment_to_placeDetailsFragment)
  }
}
