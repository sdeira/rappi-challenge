package com.example.rappichallenge.models

import com.squareup.moshi.Json
import java.io.Serializable

data class NearbyRestaurants(
    @Json(name = "nearby_restaurants")
    val nearbyRestaurants: List<Restaurant>
) : Serializable