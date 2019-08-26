package com.example.rappichallenge.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "restaurants")
data class Restaurant(
    @Json(name = "id")
    @PrimaryKey
    val id: String,

    @Json(name = "name")
    val name: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "average_cost_for_two")
    val averageCostForTwo: String?,

    @Json(name = "currency")
    val currency: String?,

    @Json(name = "thumb")
    val thumb: String?,

    @Json(name = "featured_image")
    val featuredImage: String?,

    @Json(name = "user_rating")
    @ColumnInfo(name = "user_rating")
    val userRating: UserRating?,

    @Json(name = "has_online_delivery")
    val hasOnlineDelivery: String?,

    @Json(name = "is_delivering_now")
    val isDeliveringNow: String?,

    @Json(name = "cuisines")
    val cuisines: String?,

    @Json(name = "phone_numbers")
    val phoneNumbers: String?
) : Serializable