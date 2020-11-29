package com.sanaebadi.foursquare.global

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import info.sanaebadi.placeapp.di.DaggerApplicationComponent
import javax.inject.Inject

class FoursquareApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>


    override fun onCreate() {
        super.onCreate()
        initializeAppInjector()
    }

    private fun initializeAppInjector() {
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }


}