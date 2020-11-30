package com.sanaebadi.foursquare.repository.dataSource.location

import io.reactivex.Completable
import io.reactivex.Single

interface UserLocationDataSource {

    fun getUserShowLocationChanged(): Single<Boolean>
    fun setUserShowLocationChanged(): Completable

}