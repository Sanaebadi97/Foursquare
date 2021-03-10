package com.sanaebadi.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FoursquareApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}