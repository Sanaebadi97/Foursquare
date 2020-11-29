package com.sanaebadi.foursquare.network.retrofit

import com.sanaebadi.data.BuildConfig
import com.sanaebadi.foursquare.entity.dto.ResponseDto
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesRetrofitApiService {
    @GET(value = "${BuildConfig.API_BASE_URL}venues/search")
    fun aroundVenues(
        @Query("ll") latLong: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("v") versioning: String,
        @Query("intent") intent: String,
        @Query("radius") radius: Int,
        @Query("limit") limit: Int
    ): Flowable<ResponseDto>
}