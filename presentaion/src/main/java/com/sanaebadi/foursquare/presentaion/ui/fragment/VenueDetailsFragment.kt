package com.sanaebadi.foursquare.presentaion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.presentaion.R
import com.sanaebadi.foursquare.presentaion.databinding.FragmentDetailsBinding
import com.sanaebadi.foursquare.presentaion.ui.fragment.base.BaseFragment
import com.sanaebadi.foursquare.presentaion.ui.viewModel.VenueDetailsViewModel
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.ViewModelFactory
import com.sanaebadi.foursquare.presentaion.util.loadCircleImage
import com.sanaebadi.foursquare.presentaion.util.observe
import com.sanaebadi.foursquare.presentaion.util.viewModelProvider
import javax.inject.Inject


class VenueDetailsFragment : BaseFragment() {


    private var binding: FragmentDetailsBinding? = null

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: VenueDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelStuff()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    private fun viewModelStuff() {

        viewModel = viewModelProvider(factory)
        arguments?.getString("id")?.let { viewModel.setId(it) }
        observe(viewModel.venueDetails, ::observerVenuesDetails)
    }

    private fun observerVenuesDetails(venueObject: VenueObject) {

        binding?.textVenuesName?.text = venueObject.name
        binding?.textVenuesCategory?.text =
            getString(R.string.category_title, venueObject.categoryName)
        binding?.textVenueAddress?.text = getString(R.string.address_title, venueObject.address)
        binding?.textVenueContact?.text = venueObject.contact
        binding?.textVenueWebsite?.text = venueObject.website
        binding?.textVenueDistance?.text = getString(R.string.distance_title, venueObject.distance)
        binding?.imageCategoryIcon?.loadCircleImage(venueObject.categoryIcon!!)
    }


    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun showMessage(resourceId: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(id: String) = VenueDetailsFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }


}