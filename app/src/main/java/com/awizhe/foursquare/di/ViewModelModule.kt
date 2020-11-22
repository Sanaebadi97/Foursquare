package com.awizhe.foursquare.di

import androidx.lifecycle.ViewModelProvider
import com.awizhe.foursquare.global.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?


}