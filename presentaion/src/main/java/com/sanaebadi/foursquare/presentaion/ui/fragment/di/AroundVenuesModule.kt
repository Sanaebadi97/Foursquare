package com.sanaebadi.foursquare.presentaion.ui.fragment.di

import androidx.fragment.app.Fragment
import com.sanaebadi.foursquare.presentaion.di.base.PerFragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.AroundVenueFragment
import dagger.Binds
import dagger.Module

@Module
abstract class AroundVenuesModule {

    @Binds
    @PerFragment
    abstract fun fragment(homeFragment: AroundVenueFragment): Fragment
}