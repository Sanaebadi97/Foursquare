package com.sanaebadi.foursquare.repository.dataSourceImpl

import com.sanaebadi.domain.model.ExploreVenuesRequestObject
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.domain.repository.VenuesRepository
import com.sanaebadi.foursquare.repository.dataSource.venues.VenuesDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(
    private val dataSource: VenuesDataSource
) : VenuesRepository {

    override fun getVenues(): Flowable<List<VenueObject>> =
        dataSource.getVenues().map { it ->
            it.map {
                it.map()
            }
        }

    override fun getVenueById(id: String): Flowable<VenueObject> =     dataSource.getVenueById(id).map {
        it.map()
    }


    override fun exploreVenues(location: ExploreVenuesRequestObject): Completable =
        dataSource.exploreVenues(location.lat, location.long)

    override fun loadMoreVenues(location: ExploreVenuesRequestObject): Completable =
        dataSource.exploreVenues(location.lat, location.lat)


}