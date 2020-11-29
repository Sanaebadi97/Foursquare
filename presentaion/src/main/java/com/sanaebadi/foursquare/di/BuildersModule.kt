package info.sanaebadi.placeapp.di

import com.sanaebadi.foursquare.ui.MainActivity
import com.sanaebadi.foursquare.di.ActivityModule
import com.sanaebadi.foursquare.di.ActivityScope
import com.sanaebadi.foursquare.ui.fragment.NearPlaceFragment
import com.sanaebadi.foursquare.ui.fragment.PlaceDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    internal abstract fun bindProductsPlaceFragment(): NearPlaceFragment


    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    internal abstract fun bindProductsDetailsFragment(): PlaceDetailsFragment


}