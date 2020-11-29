package com.sanaebadi.foursquare.network.retrofit

import com.sanaebadi.data.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AbstractService @Inject constructor() {

    companion object {
        const val TIME_OUT_CONNECTION: Long = 30
        const val TIME_OUT_API: Long = 30
    }

    fun <T> create(serviceClass: Class<T>): T =
        retrofit(TIME_OUT_API).create(serviceClass)

    private fun retrofit(type: Long): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClientBuilder(type).build())
        .build()


    private fun okHttpClientBuilder(timeout: Long): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor())

        return builder
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}