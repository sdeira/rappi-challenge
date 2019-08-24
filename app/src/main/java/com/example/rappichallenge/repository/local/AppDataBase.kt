package com.example.rappichallenge.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rappichallenge.models.Restaurant

@Database(entities = arrayOf(Restaurant::class), version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun restaurantsDAO() : RestaurantsDAO
}