package com.sanaebadi.app

import android.app.Application
import com.sanaebadi.foursquare.di.base.PerActivity
import com.sanaebadi.foursquare.ui.activity.home.VenuesActivity
import com.sanaebadi.foursquare.ui.activity.home.VenuesActivityViewModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun application(app: FoursquareApplication): Application

    @PerActivity
    @ContributesAndroidInjector(modules = [VenuesActivityViewModule::class])
    abstract fun homeActivityInjector(): VenuesActivity

}