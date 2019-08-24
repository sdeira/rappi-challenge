package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.BuildConfig
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class AppRepositoryImplementation @Inject constructor(private val appService: AppService) : AppRepository {

    override fun getRestaurantsFromApi(lat: String, long: String): Observable<NearbyRestaurants> {
        return appService.getNearbyRestaurants(lat, long)
    }
}