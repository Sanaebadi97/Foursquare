package com.sanaebadi.foursquare.presentaion.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.sanaebadi.domain.interactors.VenueDetailsUseCase
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.BaseViewModel
import com.sanaebadi.foursquare.presentaion.util.switchMap
import javax.inject.Inject

class VenueDetailsViewModel @Inject constructor(
    private val venueDetailsUseCase: VenueDetailsUseCase
) : BaseViewModel() {

    private val venueId = MutableLiveData<String>()
    val venueDetails: LiveData<VenueObject> = venueId.switchMap {
        LiveDataReactiveStreams.fromPublisher(
            venueDetailsUseCase(it)
        )
    }

    fun setId(id: String) {
        venueId.value = id
    }
}
