package com.sanaebadi.foursquare.presentaion.di

import com.sanaebadi.domain.executor.PostExecutionThread
import com.sanaebadi.foursquare.presentaion.executors.UiThread
import dagger.Binds
import dagger.Module

@Module
abstract class ExecutionModule {

    @Binds
    abstract fun postExecutionThread(uiThread: UiThread): PostExecutionThread

}