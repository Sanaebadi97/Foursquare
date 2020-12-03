package com.sanaebadi.foursquare.ui.fragment.di

import androidx.fragment.app.Fragment
import com.sanaebadi.foursquare.di.base.PerFragment
import com.sanaebadi.foursquare.ui.fragment.VenueDetailsFragment
import com.sanaebadi.foursquare.ui.viewModel.di.VenuesDetailsViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [VenuesDetailsViewModelModule::class])
abstract class VenuesDetailsModule {
    @Binds
    @PerFragment
    abstract fun fragment(detailsFragment: VenueDetailsFragment): Fragment
}