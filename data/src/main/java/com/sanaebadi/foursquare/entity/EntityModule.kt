package com.sanaebadi.foursquare.entity

import android.app.Application
import androidx.room.Room
import com.sanaebadi.foursquare.entity.db.VenuesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EntityModule {

    @Provides
    fun venuesDao(db: VenuesDatabase) = db.venuesDao()

    @Provides
    @Singleton
    fun database(application: Application): VenuesDatabase = Room.databaseBuilder(
        application.applicationContext,
        VenuesDatabase::class.java,
        "venues_db"
    ).fallbackToDestructiveMigration()
        .build()
}