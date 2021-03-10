package com.sanaebadi.foursquare.repository.dataSource.location

import com.sanaebadi.foursquare.preferences.PreferencesHelper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserLocationDataSourceImpl @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : UserLocationDataSource {

    override fun getUserShowLocationChanged(): Single<Boolean> {
        return Single.fromCallable {
            preferencesHelper.getUserLocationChanged()
        }
    }

    override fun setUserShowLocationChanged(): Completable {
        return Completable.fromCallable {
            preferencesHelper.setUserLocationChanged()
        }
    }


}