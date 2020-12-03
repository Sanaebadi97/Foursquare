package com.sanaebadi.foursquare.ui.fragment.di

import androidx.fragment.app.Fragment
import com.sanaebadi.foursquare.di.base.PerFragment
import com.sanaebadi.foursquare.ui.fragment.AroundVenueFragment
import dagger.Binds
import dagger.Module

@Module
abstract class AroundVenuesModule {

    @Binds
    @PerFragment
    abstract fun fragment(homeFragment: AroundVenueFragment): Fragment
}