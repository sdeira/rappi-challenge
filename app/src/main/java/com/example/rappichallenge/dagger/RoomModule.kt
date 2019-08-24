package com.example.rappichallenge.dagger

import android.app.Application
import androidx.room.Room
import com.example.rappichallenge.repository.local.AppDataBase
import com.example.rappichallenge.repository.local.RestaurantsDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase = Room.databaseBuilder(app,
        AppDataBase::class.java, "app_db").build()

    @Provides
    @Singleton
    fun provideRestaurantsDao(database: AppDataBase): RestaurantsDAO = database.restaurantsDAO()
}