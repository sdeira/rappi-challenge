package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.BuildConfig
import com.example.rappichallenge.models.NearbyRestaurants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AppService {
    companion object {
        const val BaseUrl = "https://developers.zomato.com/api/v2.1/"
    }

    object QueryParams {
        const val UserKey = "user-key"
        const val Lat = "lat"
        const val Long = "long"
    }

    object Paths {
        const val Geocode = "geocode"
    }

    @Headers("user-key: " + BuildConfig.ZomatoApiKey)
    @GET(Paths.Geocode)
    fun getNearbyRestaurants(
        @Query(QueryParams.Lat) lat: String,
        @Query(QueryParams.Long) long: String
    ): Observable<NearbyRestaurants>
}