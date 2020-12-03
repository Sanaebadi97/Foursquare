package com.sanaebadi.app

import com.sanaebadi.domain.di.DomainModule
import com.sanaebadi.foursquare.di.DataModule
import com.sanaebadi.foursquare.di.PresentationModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        PresentationModule::class,
        DomainModule::class
    ]
)
interface AppComponent : AndroidInjector<FoursquareApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<FoursquareApplication>()
}
