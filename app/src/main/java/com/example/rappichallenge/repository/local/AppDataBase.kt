package com.example.rappichallenge.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.models.UserRating

@Database(entities = arrayOf(Restaurant::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun restaurantsDAO() : RestaurantsDAO
}