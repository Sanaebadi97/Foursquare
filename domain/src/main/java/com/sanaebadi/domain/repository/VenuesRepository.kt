package com.sanaebadi.domain.repository

import com.farahani.elmira.domain.entity.ExploreVenuesRequestObject
import com.farahani.elmira.domain.entity.VenueObject
import io.reactivex.Completable
import io.reactivex.Flowable

interface VenuesRepository {

    fun exploreVenues(location: ExploreVenuesRequestObject): Completable

    fun loadMoreVenues(location: ExploreVenuesRequestObject): Completable

    fun getVenues(): Flowable<List<VenueObject>>

    fun getVenueById(id: String): Flowable<VenueObject>
}