package com.example.rappichallenge.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rappichallenge.models.Restaurant
import io.reactivex.Observable

@Dao
interface RestaurantsDAO {
    @Query("SELECT * FROM restaurants")
    fun queryRestaurants(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM restaurants WHERE id = :id")
    fun queryRestaurant(id: String): Observable<Restaurant>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertRestaurant(restaurant: Restaurant)
}