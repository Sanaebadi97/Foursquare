package com.sanaebadi.foursquare.repository.dataSourceImpl

import com.sanaebadi.domain.repository.UserLocationRepository
import com.sanaebadi.foursquare.repository.dataSource.location.UserLocationDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    private val dataSource: UserLocationDataSource
) : UserLocationRepository {

    override fun setShowLocationChanged(): Completable = dataSource.setUserShowLocationChanged()

    override fun getShowLocationChanged(): Single<Boolean> = dataSource.getUserShowLocationChanged()
}