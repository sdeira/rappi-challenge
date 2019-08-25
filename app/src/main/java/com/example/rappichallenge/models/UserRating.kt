package com.example.rappichallenge.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

data class UserRating(
    @Json(name = "aggregate_rating")
    val aggregateRating: String,

    @Json(name = "rating_color")
    val ratingColor: String
) : Serializable