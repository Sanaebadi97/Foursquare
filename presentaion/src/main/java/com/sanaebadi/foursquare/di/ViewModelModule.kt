package com.sanaebadi.foursquare.di

import androidx.lifecycle.ViewModelProvider
import com.sanaebadi.foursquare.global.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?


}