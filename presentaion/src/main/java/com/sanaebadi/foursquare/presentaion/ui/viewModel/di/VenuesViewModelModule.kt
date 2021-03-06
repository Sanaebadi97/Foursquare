package com.sanaebadi.foursquare.presentaion.ui.viewModel.di

import androidx.lifecycle.ViewModel
import com.sanaebadi.foursquare.presentaion.di.base.PerActivity
import com.sanaebadi.foursquare.presentaion.di.base.ViewModelKey
import com.sanaebadi.foursquare.presentaion.ui.viewModel.VenuesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VenuesViewModelModule {

    @Binds
    @PerActivity
    @IntoMap
    @ViewModelKey(VenuesViewModel::class)
    abstract fun viewModel(viewModel: VenuesViewModel): ViewModel
}