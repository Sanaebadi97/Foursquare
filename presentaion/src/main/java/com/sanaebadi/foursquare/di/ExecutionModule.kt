package com.sanaebadi.foursquare.di

import com.sanaebadi.domain.executor.PostExecutionThread
import com.sanaebadi.foursquare.executors.UiThread
import dagger.Binds
import dagger.Module

@Module
abstract class ExecutionModule {

    @Binds
    abstract fun postExecutionThread(uiThread: UiThread): PostExecutionThread

}