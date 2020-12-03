package com.sanaebadi.foursquare.ui.activity.home.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.sanaebadi.foursquare.di.base.PerActivity
import com.sanaebadi.foursquare.ui.viewModel.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
abstract class BaseActivityModule {

    @Module
    companion object {
        const val ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager"

        @JvmStatic
        @Provides
        @Named(ACTIVITY_FRAGMENT_MANAGER)
        @PerActivity
        fun provideActivityFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }
    }

    @Binds
    @PerActivity
    abstract fun activity(appCompatActivity: AppCompatActivity): Activity

    @Binds
    @PerActivity
    abstract fun activityContext(activity: Activity): Context

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelFactory): ViewModelProvider.Factory
}
