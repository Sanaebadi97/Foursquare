package com.sanaebadi.foursquare.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.sanaebadi.foursquare.databinding.FragmentAroundVenueBinding
import com.sanaebadi.foursquare.ui.adapter.VenuesAdapter
import com.sanaebadi.foursquare.ui.fragment.base.BaseFragment
import com.sanaebadi.foursquare.ui.viewModel.VenuesViewModel
import com.sanaebadi.foursquare.ui.viewModel.base.ViewModelFactory
import com.sanaebadi.foursquare.util.linearLayout
import com.sanaebadi.foursquare.util.observe
import com.sanaebadi.foursquare.util.viewModelProvider
import javax.inject.Inject


class AroundVenueFragment : BaseFragment() {

    private var binding: FragmentAroundVenueBinding? = null

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: VenuesViewModel
    private lateinit var adapter: VenuesAdapter


    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel = viewModelProvider(factory)

        adapter = VenuesAdapter { holder ->
            viewModel.observeClicks(holder.observe())
        }

        viewModel.loadMoreObserver(adapter.getLoadMoreObservable())
        observe(viewModel.messageObservable, ::showMessage)
        observe(viewModel.venues, adapter::addItems)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAroundVenueBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding?.recyclerAroundPlace?.linearLayout(requireActivity())
        binding?.recyclerAroundPlace?.adapter = adapter

        binding?.progressBar?.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun showMessage(resourceId: Int) {
        TODO("Not yet implemented")
    }


    companion object {
        fun newInstance() = AroundVenueFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }


}