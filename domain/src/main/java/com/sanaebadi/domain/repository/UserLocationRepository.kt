package com.sanaebadi.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface UserLocationRepository {

    fun setShowLocationChanged(): Completable

    fun getShowLocationChanged(): Single<Boolean>
}