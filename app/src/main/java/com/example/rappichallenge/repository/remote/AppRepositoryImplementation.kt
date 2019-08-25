package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.BuildConfig
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.local.RestaurantsDAO
import io.reactivex.Observable
import javax.inject.Inject

class AppRepositoryImplementation @Inject constructor(private val appService: AppService,
                                                      val restaurantsDAO: RestaurantsDAO)
    : AppRepository {

    override fun getRestaurantsFromApi(lat: String, long: String): Observable<NearbyRestaurants> {
        return appService.getNearbyRestaurants(lat, long).doOnNext {
            for (restaurant in it.nearbyRestaurants) {
                restaurantsDAO.insertRestaurant(restaurant)
            }
        }
    }
}