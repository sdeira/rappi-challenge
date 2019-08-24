package com.example.rappichallenge.models

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
    val name: String?
) : Serializable