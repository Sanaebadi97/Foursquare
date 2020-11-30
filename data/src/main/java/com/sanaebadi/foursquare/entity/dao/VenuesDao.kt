package com.sanaebadi.foursquare.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanaebadi.foursquare.entity.VenueEntity
import io.reactivex.Flowable


@Dao
interface VenuesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(venues: List<VenueEntity>)

    @Query("SELECT * FROM aroundVenue")
    fun selectAll(): Flowable<List<VenueEntity>>

    @Query("SELECT * FROM aroundVenue WHERE id =:id")
    fun selectVenue(id: String): Flowable<VenueEntity>
}