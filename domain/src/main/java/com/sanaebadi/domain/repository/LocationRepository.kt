package com.farahani.elmira.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by elmira on 20, March, 2020
 */
interface LocationRepository {

    fun setShowLocationChanged(): Completable

    fun getShowLocationChanged(): Single<Boolean>
}