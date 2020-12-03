package com.sanaebadi.foursquare.preferences

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class PreferencesModule {
    @Binds
    @Singleton
    abstract fun appPreferences(appPreferences: AppPreferences): PreferencesHelper
}
