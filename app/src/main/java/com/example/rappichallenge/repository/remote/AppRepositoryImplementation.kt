package com.example.rappichallenge.repository.remote

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.example.rappichallenge.BuildConfig
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.local.RestaurantsDAO
import io.reactivex.Observable
import javax.inject.Inject

class AppRepositoryImplementation @Inject constructor(private val appService: AppService,
                                                      val restaurantsDAO: RestaurantsDAO)
    : AppRepository {

    override fun getRestaurantFromDB(id: String): Observable<Restaurant> {
        return restaurantsDAO.queryRestaurant(id)
    }

    override fun getRestaurantsFromDB() : Observable<List<Restaurant>> {
        return restaurantsDAO.queryRestaurants().toObservable()
    }

    override fun getRestaurantsFromApi(lat: String, long: String): Observable<NearbyRestaurants> {
        return appService.getNearbyRestaurants(lat, long).doOnNext {
            for (restaurant in it.nearbyRestaurants) {
                restaurantsDAO.insertRestaurant(restaurant)
            }
        }
    }
}