package com.sanaebadi.foursquare.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanaebadi.foursquare.entity.local.VenueEntity
import io.reactivex.Flowable


@Dao
interface VenuesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(venues: List<VenueEntity>)

    @Query("SELECT * FROM venues")
    fun selectAll(): Flowable<List<VenueEntity>>

    @Query("SELECT * FROM venues WHERE id =:id")
    fun selectVenue(id: String): Flowable<VenueEntity>
}