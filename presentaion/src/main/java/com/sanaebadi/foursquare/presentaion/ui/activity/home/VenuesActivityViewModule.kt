package com.sanaebadi.foursquare.presentaion.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.sanaebadi.foursquare.presentaion.di.base.PerActivity
import com.sanaebadi.foursquare.presentaion.di.base.PerFragment
import com.sanaebadi.foursquare.presentaion.ui.activity.home.base.BaseActivityModule
import com.sanaebadi.foursquare.presentaion.ui.fragment.AroundVenueFragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.VenueDetailsFragment
import com.sanaebadi.foursquare.presentaion.ui.fragment.di.AroundVenuesModule
import com.sanaebadi.foursquare.presentaion.ui.fragment.di.VenuesDetailsModule
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.OnLocationCallback
import com.sanaebadi.foursquare.presentaion.ui.viewModel.di.LocationViewModelModule
import com.sanaebadi.foursquare.presentaion.ui.viewModel.di.VenuesViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(
    includes = [
        VenuesViewModelModule::class,
        LocationViewModelModule::class,
        BaseActivityModule::class,
    ]
)
abstract class VenuesActivityViewModule {

    @Binds
    @PerActivity
    abstract fun appCompatActivity(venuesActivity: VenuesActivity): AppCompatActivity

    @Binds
    @PerActivity
    abstract fun locationCallback(homeActivity1: VenuesActivity): OnLocationCallback

    @PerFragment
    @ContributesAndroidInjector(modules = [AroundVenuesModule::class])
    abstract fun homeFragmentInjector(): AroundVenueFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [VenuesDetailsModule::class])
    abstract fun detailsFragmentInjector(): VenueDetailsFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLifecycle(homeActivity: VenuesActivity): Lifecycle =
            homeActivity.lifecycle
    }
}