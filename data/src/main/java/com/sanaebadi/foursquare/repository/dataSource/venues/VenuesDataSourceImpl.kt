package com.sanaebadi.foursquare.repository.dataSource.venues

import com.sanaebadi.data.BuildConfig
import com.sanaebadi.foursquare.entity.VenueEntity
import com.sanaebadi.foursquare.entity.dao.VenuesDao
import com.sanaebadi.foursquare.mapper.mapDomainToEntity
import com.sanaebadi.foursquare.network.retrofit.VenuesRetrofitApiService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class VenuesDataSourceImpl @Inject constructor(
    private val dataService: VenuesRetrofitApiService,
    private val venuesDao: VenuesDao
) : VenuesDataSource {


    @Volatile
    private var offset: Int = 1

    override fun exploreVenues(lat: String, long: String): Completable =
        getData(lat, long)


    override fun getVenues(): Flowable<List<VenueEntity>> = venuesDao.selectAll()

    override fun getVenueById(id: String): Flowable<VenueEntity> = venuesDao.selectVenue(id)

    private fun getData(lat: String, long: String): Completable {
        return getVenuesByServer(lat, long)
            .doOnSuccess {
                offset = offset.plus(1)
                venuesDao.insert(it)
            }.ignoreElement()
    }

    private fun getVenuesByServer(lat: String, long: String): Single<List<VenueEntity>> {
        return dataService.exploreAroundVenues(
            "$lat,$long",
            BuildConfig.API_CLIENT_ID,
            BuildConfig.API_CLIENT_SECRET,
            BuildConfig.API_VERSION,
            "browse",
            100000,
            50,
            offset,
            1
        ).flatMapIterable {
            it.response.groups[0].items
        }
            .map {
                it.venue.mapDomainToEntity()
            }.toList()
    }
}