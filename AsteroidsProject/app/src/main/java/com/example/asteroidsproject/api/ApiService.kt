package com.example.asteroidsproject.api

import com.example.asteroidsproject.model.PictureOfDay
import com.example.asteroidsproject.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object Network {
    private val retrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val asteroids: AsteroidService =
        retrofitClient.create(AsteroidService::class.java)
}

interface AsteroidService {
    @GET("neo/rest/v1/feed")
    fun getAsteroidsAsync(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Deferred<ResponseBody>

    @GET("planetary/apod")
    fun getPictureOfDayAsync(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Deferred<PictureOfDay>
}