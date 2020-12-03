package com.sanaebadi.foursquare.repository.dataSource

import com.sanaebadi.foursquare.repository.dataSource.location.UserLocationDataSource
import com.sanaebadi.foursquare.repository.dataSource.location.UserLocationDataSourceImpl
import com.sanaebadi.foursquare.repository.dataSource.venues.VenuesDataSource
import com.sanaebadi.foursquare.repository.dataSource.venues.VenuesDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun venuesDataSource(venuesDataSource: VenuesDataSourceImpl): VenuesDataSource

    @Binds
    @Singleton
    abstract fun locationDataSource(locationDataSource: UserLocationDataSourceImpl): UserLocationDataSource
}