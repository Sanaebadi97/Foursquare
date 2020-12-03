package com.sanaebadi.foursquare.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.sanaebadi.foursquare.ui.viewModel.base.BaseViewModel
import com.sanaebadi.foursquare.ui.viewModel.base.Event
import javax.inject.Inject

class LocationViewModel @Inject constructor() : BaseViewModel() {

    val hasLocation = MutableLiveData<Event<Boolean>>()
    val isLocating = MutableLiveData<Event<Boolean>>()

    fun noLocationAvailable() {
        hasLocation.value = Event(false)
    }

    fun setLocating(locating: Boolean) {
        isLocating.value = Event(locating)
    }
}