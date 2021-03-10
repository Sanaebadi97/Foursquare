package com.sanaebadi.foursquare.executor

import com.sanaebadi.domain.executor.ThreadExecutor
import dagger.Binds
import dagger.Module

@Module
abstract class ExecutionModule {

    @Binds
    abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}