package com.sanaebadi.foursquare.presentaion.ui.activity.home

import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.sanaebadi.foursquare.presentaion.R
import com.sanaebadi.foursquare.presentaion.databinding.ActivityVenuesBinding
import com.sanaebadi.foursquare.presentaion.ui.activity.home.base.BaseActivity
import com.sanaebadi.foursquare.presentaion.ui.adapter.base.BaseAction
import com.sanaebadi.foursquare.presentaion.ui.adapter.base.VenueAction
import com.sanaebadi.foursquare.presentaion.ui.fragment.AroundVenueFragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.VenueDetailsFragment
import com.sanaebadi.foursquare.presentaion.ui.viewModel.LocationViewModel
import com.sanaebadi.foursquare.presentaion.ui.viewModel.VenuesViewModel
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.LocationManager
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.OnLocationCallback
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.ViewModelFactory
import com.sanaebadi.foursquare.presentaion.util.observe
import com.sanaebadi.foursquare.presentaion.util.viewModelProvider
import javax.inject.Inject

class VenuesActivity : BaseActivity(), OnLocationCallback {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: VenuesViewModel

    @Inject
    lateinit var locationManager: LocationManager

    private lateinit var locationSelectViewModel: LocationViewModel

    private lateinit var binding: ActivityVenuesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenuesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, AroundVenueFragment.newInstance())

        }

        locationManager.enable(true)
        viewModel = viewModelProvider(factory)
        locationSelectViewModel = viewModelProvider(factory)

        observe(viewModel.clickObservable, ::observeClicks)
    }

    private fun observeClicks(action: BaseAction) {
        when (action) {
            is VenueAction -> {
                replaceFragment(
                    R.id.fragmentContainer,
                    VenueDetailsFragment.newInstance(action.id),
                    true
                )

            }
        }
    }

    override fun onStartLocating() = locationSelectViewModel.setLocating(true)

    override fun onNewLocation(location: Location?) {
        if (location == null) {
            locationSelectViewModel.noLocationAvailable()
        } else {
            viewModel.exploreVenues(location.latitude.toString(), location.longitude.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        locationManager.onActivityResult(requestCode, resultCode)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        locationManager.onRequestPermissionsResult(requestCode, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}