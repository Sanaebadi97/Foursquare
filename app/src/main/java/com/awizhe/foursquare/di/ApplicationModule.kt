package com.awizhe.foursquare.di

import android.content.Context
import com.awizhe.domain.executor.PostExecutionThread
import com.awizhe.domain.executor.ThreadExecutor
import com.awizhe.foursquare.executor.JobExecutor
import com.awizhe.foursquare.executors.UIThread
import com.awizhe.foursquare.global.FoursquareApplication
import com.awizhe.foursquare.util.AppSchedulerProvider
import com.awizhe.foursquare.util.ConnectionHelper
import com.awizhe.foursquare.util.PreferencesHelper
import com.awizhe.foursquare.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    //TODO:DEFINE REPOSITORY AND CACHE HERE

    @Provides
    internal fun provideContext(application: FoursquareApplication): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    internal fun providesThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providesPostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

//    @Provides
//    @Singleton
//    fun providePlaceRepository(placeDataRepository: PlaceDataRepository): PlaceRepository {
//        return placeDataRepository
//    }


    @Provides
    @Singleton
    fun provideConnectionHelper(context: Context) = ConnectionHelper(context)

    @Provides
    @Singleton
    fun providePreferencesHelper(context: Context) = PreferencesHelper(context)


    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}