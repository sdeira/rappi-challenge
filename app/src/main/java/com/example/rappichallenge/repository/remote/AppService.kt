package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.models.Restaurant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    companion object {
        const val BaseUrl = "https://developers.zomato.com/api/v2.1/"
    }

    object QueryParams {
        const val ApiKey = "api_key"
        const val Geocode = "geocode"
    }

    @GET()
    fun getNearbyRestaurants(
        @Query(QueryParams.ApiKey) apiKey: String,
        @Query(QueryParams.Geocode) geocode: String
    ): Observable<List<Restaurant>>
}