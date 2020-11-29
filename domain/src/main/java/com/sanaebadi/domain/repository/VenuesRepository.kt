package com.sanaebadi.domain.repository

import com.sanaebadi.domain.model.ExploreVenuesRequestObject
import com.sanaebadi.domain.model.VenueObject
import io.reactivex.Completable
import io.reactivex.Flowable

interface VenuesRepository {

    fun exploreVenues(location: ExploreVenuesRequestObject): Completable

    fun loadMoreVenues(location: ExploreVenuesRequestObject): Completable

    fun getVenues(): Flowable<List<VenueObject>>

    fun getVenueById(id: String): Flowable<VenueObject>
}