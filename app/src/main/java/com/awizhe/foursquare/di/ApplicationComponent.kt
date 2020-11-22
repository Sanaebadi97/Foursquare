package com.awizhe.foursquare.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.awizhe.foursquare.global.FoursquareApplication
import info.sanaebadi.placeapp.di.ApplicationModule
import info.sanaebadi.placeapp.di.BuildersModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, BuildersModule::class, ViewModelModule::class])

interface ApplicationComponent : AndroidInjector<FoursquareApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: FoursquareApplication): Builder

        fun build(): ApplicationComponent
    }
}