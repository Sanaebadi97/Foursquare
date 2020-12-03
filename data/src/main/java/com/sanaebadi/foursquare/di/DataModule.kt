package com.sanaebadi.foursquare.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.sanaebadi.foursquare.entity.EntityModule
import com.sanaebadi.foursquare.executor.ExecutionModule
import com.sanaebadi.foursquare.network.NetworkModule
import com.sanaebadi.foursquare.preferences.PreferencesModule
import com.sanaebadi.foursquare.repository.dataSourceImpl.RepositoryModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        EntityModule::class,
        RepositoryModule::class,
        ExecutionModule::class,
        NetworkModule::class,
        PreferencesModule::class
    ]
)
abstract class DataModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        fun sharedPreferences(application: Application): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(application)
        }
    }
}