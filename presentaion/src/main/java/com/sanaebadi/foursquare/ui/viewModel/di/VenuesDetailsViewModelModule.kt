package com.sanaebadi.foursquare.ui.viewModel.di

import androidx.lifecycle.ViewModel
import com.sanaebadi.foursquare.di.base.PerFragment
import com.sanaebadi.foursquare.di.base.ViewModelKey
import com.sanaebadi.foursquare.ui.viewModel.VenueDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class VenuesDetailsViewModelModule {
    @Binds
    @IntoMap
    @PerFragment
    @ViewModelKey(VenueDetailsViewModel::class)
    abstract fun viewModel(viewModel: VenueDetailsViewModel): ViewModel
}
