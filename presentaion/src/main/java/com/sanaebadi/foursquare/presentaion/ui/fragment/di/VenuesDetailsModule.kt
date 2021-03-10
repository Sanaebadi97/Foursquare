package com.sanaebadi.foursquare.presentaion.ui.fragment.di

import androidx.fragment.app.Fragment
import com.sanaebadi.foursquare.presentaion.di.base.PerFragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.VenueDetailsFragment
import com.sanaebadi.foursquare.presentaion.ui.viewModel.di.VenuesDetailsViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [VenuesDetailsViewModelModule::class])
abstract class VenuesDetailsModule {
    @Binds
    @PerFragment
    abstract fun fragment(detailsFragment: VenueDetailsFragment): Fragment
}