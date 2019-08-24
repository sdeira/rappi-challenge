package com.example.rappichallenge.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rappichallenge.models.Restaurant

@Dao
interface RestaurantsDAO {
    @Query("SELECT * FROM restaurants")
    fun queryCryptocurrencies(): LiveData<List<Restaurant>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertCryptocurrency(cryptocurrency: Restaurant)
}