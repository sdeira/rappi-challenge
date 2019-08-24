package com.example.rappichallenge

import com.example.rappichallenge.models.Restaurant
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson
import java.io.Serializable

class NearbyRestaurantsAdapterFactory {

    @FromJson
    fun arrayListFromJson(list: List<RestaurantObject>) : List<Restaurant> {
        val restaurantList = ArrayList<Restaurant>(list.size)
        for (restaurantObject in list) {
            restaurantList.add(restaurantObject.restaurant)
        }
        return restaurantList
    }

    data class RestaurantObject(
        @Json(name = "restaurant")
        val restaurant: Restaurant
    ) : Serializable
}