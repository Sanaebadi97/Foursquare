package com.sanaebadi.foursquare.repository.dataSource.venues

import com.sanaebadi.foursquare.entity.VenueEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface VenuesDataSource {

    fun exploreVenues(lat: String, long: String): Completable
    fun getVenues(): Flowable<List<VenueEntity>>
    fun getVenueById(id: String): Flowable<VenueEntity>
}