package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import io.reactivex.Observable

interface AppRepository {
    fun getRestaurantsFromApi(lat: String, long: String): Observable<NearbyRestaurants>
}