package com.sanaebadi.foursquare.entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanaebadi.foursquare.entity.dao.VenuesDao
import com.sanaebadi.foursquare.entity.local.VenueEntity

@Database(
    entities = [
        VenueEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PlacesDatabase : RoomDatabase() {

    abstract fun venuesDao(): VenuesDao
}
