package com.sanaebadi.foursquare.presentaion.ui.viewModel.di

import androidx.lifecycle.ViewModel
import com.sanaebadi.foursquare.presentaion.di.base.PerActivity
import com.sanaebadi.foursquare.presentaion.di.base.ViewModelKey
import com.sanaebadi.foursquare.presentaion.ui.viewModel.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LocationViewModelModule {
    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(LocationViewModel::class)
    abstract fun viewModel(viewModel: LocationViewModel): ViewModel
}