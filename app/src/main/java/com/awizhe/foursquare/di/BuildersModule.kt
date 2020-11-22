package info.sanaebadi.placeapp.di

import com.awizhe.foursquare.MainActivity
import com.awizhe.foursquare.di.ActivityModule
import com.awizhe.foursquare.di.ActivityScope
import com.awizhe.foursquare.ui.NearPlaceFragment
import com.awizhe.foursquare.ui.PlaceDetailsFragment
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