package com.sanaebadi.foursquare.repository.dataSourceImpl

import com.sanaebadi.domain.repository.UserLocationRepository
import com.sanaebadi.domain.repository.VenuesRepository
import com.sanaebadi.foursquare.repository.dataSource.DataSourceModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun venuesRepository(venuesRepository: VenuesRepositoryImpl): VenuesRepository

    @Binds
    @Singleton
    abstract fun locationRepository(locationRepository: UserLocationRepositoryImpl): UserLocationRepository
}