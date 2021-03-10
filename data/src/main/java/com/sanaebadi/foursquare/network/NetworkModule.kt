package com.sanaebadi.foursquare.network

import com.sanaebadi.foursquare.network.retrofit.AbstractService
import com.sanaebadi.foursquare.network.retrofit.VenuesRetrofitApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun venuesDataService(dataServiceProvider: AbstractService) =
        dataServiceProvider.create(VenuesRetrofitApiService::class.java)
}