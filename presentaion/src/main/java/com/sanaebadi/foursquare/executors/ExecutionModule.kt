package com.sanaebadi.foursquare.executors

import com.sanaebadi.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module

@Module
abstract class ExecutionModule {

    @Binds
    abstract fun postExecutionThread(uiThread: UiThread): PostExecutionThread

}