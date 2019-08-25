package com.example.rappichallenge.repository.local

import androidx.room.TypeConverter
import com.example.rappichallenge.models.UserRating
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun userRatingToString(userRating: UserRating) : String {
        return Gson().toJson(userRating)
    }

    @TypeConverter
    fun stringToUserRating(userRating: String) : UserRating {
        return Gson().fromJson(userRating, UserRating::class.java)
    }

}